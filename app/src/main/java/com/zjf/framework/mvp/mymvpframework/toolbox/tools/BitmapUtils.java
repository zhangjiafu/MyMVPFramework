package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zhul on 2015/12/11.
 */
public class BitmapUtils {
    private static final String TAG = BitmapUtils.class.getSimpleName();

	/*
	 * 用Base64算法加密，当字符串过长（一般超过76）时会自动在中间加一个换行符，字符串最后也会加一个换行符。导致和其他模块对接时结果不一致。
	 * 解决方法：
	 * 将
	 * android.util.Base64.encodeToString(input, Base64.DEFAULT)
	 * 换成
	 * android.util.Base64.encodeToString(input, Base64.NO_WRAP);
	 */

    /**
     * 图片转成string
     * @param bitmap
     * @return
     */
    public final String convertBitmapToString(Bitmap bitmap){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
//        bitmap.compress(CompressFormat.PNG, 100, baos);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        if(baos.toByteArray().length / 1024 > 100 ){
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        }
        byte[] appicon = baos.toByteArray();// 转为byte数组

        return Base64.encodeToString(appicon, Base64.NO_WRAP);
    }

    /**
     * string转成bitmap
     * @param str
     */
    public final Bitmap convertStringToBitmap(String str)
    {
        // OutputStream out;
        Bitmap bitmap = null;
        try
        {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(str, Base64.NO_WRAP);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Drawable转成Bitmap
     * @param drawable
     * @return
     */
    public final Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Bitmap转成Drawable
     * @param context
     * @param bitmap
     * @return
     */
    public final BitmapDrawable bitmapToDrawable(Context context,Bitmap bitmap){
        BitmapDrawable bd= new BitmapDrawable(context.getResources(), bitmap);
        return bd;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public final Bitmap blur(Context context, Bitmap bkg, View view) {
        float scaleFactor = 32;
        float radius = 3;

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()
                / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        // overlay = FastBlur.doBlur(overlay, (int)radius, true);

        RenderScript rs = RenderScript.create(context);

        Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs,
                overlayAlloc.getElement());
        blur.setInput(overlayAlloc);
        blur.setRadius(radius);
        blur.forEach(overlayAlloc);
        overlayAlloc.copyTo(overlay);

        return overlay;
        // view.setBackground(new BitmapDrawable(getResources(), overlay));
    }

    public final Bitmap scaleTo(Bitmap src, int toWidth, int toHeight){
        return Bitmap.createScaledBitmap(src, toWidth, toHeight, true);
    }

    /**
     * 获取指定图片的千字节数（即：多少K）
     * @param photo 指定图片
     * @return 指定图片的字节数
     */
    public final int getBytes(Bitmap photo) {
        Log.d(TAG, "getbytes()==>");
        int bytesCount = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){    //API 19
            bytesCount = photo.getAllocationByteCount();
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){//API 12
            bytesCount = photo.getByteCount();
        }else{
            bytesCount = photo.getRowBytes() * photo.getHeight();//earlier version
        }

        return bytesCount;
    }

    /**
     * 获取指定图片的千字节数（即：多少K）
     * @param photo 指定图片
     * @return 指定图片的字节数
     */
    public final int getKbytes(Bitmap photo) {
        Log.d(TAG, "getKbytes()==>");
        int bytesCount = getBytes(photo);

        return bytesCount / 1024;
    }

    /**
     * 图片质量压缩
     * 压缩图片，直到大小  小于bitmapCompressedMaxSize
     * @param photo
     * @param bitmapCompressedMaxSize
     * @return
     */
    public final Bitmap compressBitmap(Bitmap photo, int bitmapCompressedMaxSize, int compressMaxResolution) {
        Log.d(TAG, "compressBitmap()==>");
        Log.d(TAG, "压缩图片不超过" + bitmapCompressedMaxSize + "k");
        Log.d(TAG, "压缩图片分辨率不超过" + compressMaxResolution + "px");
        if(null == photo){
            return null;
        }
        int firstCompressOption = 80;

        System.gc();// 提醒系统及时回收

        Bitmap bitmapResult = null;
        ByteArrayOutputStream baos = null;
        try {
            Bitmap bitmapProportionCompress = null;
            baos = new ByteArrayOutputStream();

//			// 根据指定分辨率进行比例压缩
//			bitmapProportionCompress = resolutionCompress(photo, 720, 1280, false);
//			// 根据指定分辨率进行比例压缩
//			bitmapProportionCompress = resolutionCompress(photo, 450, 800, false);
            // 根据指定分辨率的最长边，进行比例压缩
            bitmapProportionCompress = resolutionCompress(photo, compressMaxResolution, false);

//			bitmapProportionCompress = photo;
//			bitmapProportionCompress = photo.copy(Bitmap.Config.ARGB_4444, false);//alpha lesser quality
            // 用根据分辨率压缩后的图片进行质量压缩
            bitmapProportionCompress.compress(Bitmap.CompressFormat.JPEG, firstCompressOption, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            Log.e(TAG, "第一次压缩baos大小(kb)：" + (baos.toByteArray().length / 1024));
            Log.e(TAG, "原图大小(kb)：" + getKbytes(photo));


            // 获取原图的千字节数（即：多少K）
            int originalBitmapKbytesCount = baos.toByteArray().length / 1024;
            if(originalBitmapKbytesCount > bitmapCompressedMaxSize){

                // 初始化压缩比率
                int options = firstCompressOption;

                while (baos.toByteArray().length / 1024f > bitmapCompressedMaxSize) {
                    options -= 10;// 每次都减少10
                    if(options <= 0){
                        break;
                    }
                    baos.reset();// 重置baos即清空baos
                    bitmapProportionCompress.compress(Bitmap.CompressFormat.JPEG, options, baos);
                    Log.e(TAG, "while循环，每次的baos大小(kb)：" + (baos.toByteArray().length / 1024));
                }
            }

			/*
			 * 警告！！
			 * BitmapFactory.decodeStream会出bug，不能将压缩后的baos解码成bitmap
			 * 必须要使用BitmapFactory.decodeByteArray
			 */
            byte[] buffer = baos.toByteArray();
            bitmapResult = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
            Log.e(TAG, "压缩完成后baos大小(kb)：" + (buffer.length / 1024));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return bitmapResult;
    }

    /**
     * 分辨率压缩
     * @param photo 要压缩的图
     * @param limitWidthOrHeight 压缩的宽度\高度的最大值
     * @param isThumbnail 是否使用略缩图压缩方式
     * @return 返回压缩后的图
     */
    public Bitmap resolutionCompress(Bitmap photo, int limitWidthOrHeight, boolean isThumbnail) {
        Log.d(TAG, "resolutionCompress()==>");
        if(null == photo){
            return null;
        }
        if(photo.getWidth() > limitWidthOrHeight || photo.getHeight() > limitWidthOrHeight){

            // 是否width比height长（即：是否横图）
            boolean isHorizonImage = photo.getWidth() > photo.getHeight();
            float heightWidthRatio = isHorizonImage
                    ? (float)photo.getWidth() / (float)photo.getHeight()
                    : (float)photo.getHeight() / (float)photo.getWidth();

            float fltLimitWidthOrHeight = (float)limitWidthOrHeight;
            int scaledWidth = 0;
            int scaledHeight = 0;
            if(isHorizonImage){
                scaledWidth = limitWidthOrHeight;
                scaledHeight = (int)(fltLimitWidthOrHeight / heightWidthRatio);
            }else{
                scaledHeight = limitWidthOrHeight;
                scaledWidth = (int)(fltLimitWidthOrHeight / heightWidthRatio);
            }

            // 根据指定分辨率进行比例压缩
            if(isThumbnail){
                return ThumbnailUtils.extractThumbnail(photo, scaledWidth, scaledHeight, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            }else{

//	            // 缩放图片的尺寸
//				return Bitmap.createScaledBitmap(photo
//						, scaledWidth
//						, scaledHeight
//						, true);
                // 缩放图片的尺寸
                float persentScaleWidth = (float) scaledWidth / photo.getWidth();
                float persentScaleHeight = (float) scaledHeight / photo.getHeight();
                Matrix matrix = new Matrix();
                matrix.postScale(persentScaleWidth, persentScaleHeight);
                // 产生缩放后的Bitmap对象
                Bitmap resizeBitmap = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
                return resizeBitmap;
            }
        }
        return photo;
    }

    /**
     * Uri转Bitmap
     * @param context
     * @param photoUri
     * @return
     */
    public Bitmap uriToBitmap(Context context, Uri photoUri) {
        Log.d(TAG, "uriToBitmap()==>");

        try {
            // 读取uri所在的图片
            Bitmap photo = MediaStore.Images.Media.getBitmap(context.getContentResolver(), photoUri);
            Log.d(TAG, "uri转bitmap成功.");
            return photo;
        }catch (Exception e){
            Log.e(TAG, "uri转bitmap失败！！！");
            // 返回null
            return null;
        }

    }

    /**
     * 图片质量压缩
     * 压缩图片
     * @param uriPhoto
     * @param limitWidth
     * @param limitHeight
     * @return
     */
    public Bitmap compressBitmap(Context context, Uri uriPhoto, int limitWidth, int limitHeight) {
        Log.d(TAG, "compressBitmap()==>");

//        String uriPath = Uri.parse(uriPhoto.toString()).getPath();
        String uriPath = getRealPathFromURI(context, uriPhoto);
        Bitmap bitmapCompressed = null;

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uriPath, opts);
//			BitmapFactory.decodeStream(fis, null, opts);

        if(opts.outHeight > opts.outWidth){
            // 竖的照片
            opts.inSampleSize = calculateInSampleSize(opts,limitWidth,limitHeight);
        }else{
            // 横的照片
            opts.inSampleSize = calculateInSampleSize(opts, limitHeight, limitWidth);
        }
//			opts.inSampleSize = calculateInSampleSize(opts,1080,1920);


        opts.inJustDecodeBounds = false;
//			opts.inPreferredConfig = Bitmap.Config.ARGB_4444;    // 默认是Bitmap.Config.ARGB_8888
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//			Bitmap bitmap = BitmapFactory.decodeStream(fis, null, opts);
        bitmapCompressed = BitmapFactory.decodeFile(uriPath, opts);

        return bitmapCompressed;
    }

    public int calculateInSampleSize(BitmapFactory.Options options,
                                     int reqWidth, int reqHeight) {
        Log.d(TAG, "calculateInSampleSize()==>");
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
//            if(inSampleSize % 2 != 0){
//            	if(inSampleSize - 1 > 1){
//            		inSampleSize -= 1;
//            	}
//            }
        }

        return inSampleSize;
    }

//    public String getRealPathFromURI(Context context, Uri contentUri) {
//        String res = null;
//        String[] proj = { MediaStore.Images.Media.DATA };
//        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
//        if(cursor == null){
//            res = contentUri.getPath();
//        }else {
//            if (cursor.moveToFirst()) {
//                ;
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                res = cursor.getString(column_index);
//            }
//            cursor.close();
//        }
//        return res;
//    }

    /**
     * <br>功能简述:4.4及以上获取图片的方法
     * <br>功能详细描述:
     * <br>注意:
     * @param context
     * @param uri
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getRealPathFromURI(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] { split[1] };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
