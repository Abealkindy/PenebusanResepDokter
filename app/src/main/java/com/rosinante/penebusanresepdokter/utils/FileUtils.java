package com.rosinante.penebusanresepdokter.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.List;


public class FileUtils {
    public static void openFile(Context context, File url) throws ActivityNotFoundException {
        // Create URI
        //Uri uri = Uri.fromFile(url);

        //TODO you want to use this method then create file provider in androidmanifest.xml with fileprovider name

        Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", url);

        String urlString = url.toString().toLowerCase();

        Intent intent = new Intent(Intent.ACTION_VIEW);

        /*
          Security
         */
        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        // Check what kind of file you are trying to open, by comparing the url with extensions.
        // When the if condition is matched, plugin sets the correct intent (mime) type,
        // so Android knew what application to use to open the file
        if (urlString.toLowerCase().toLowerCase().contains(".doc")
                || urlString.toLowerCase().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if (urlString.toLowerCase().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if (urlString.toLowerCase().contains(".ppt")
                || urlString.toLowerCase().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if (urlString.toLowerCase().contains(".xls")
                || urlString.toLowerCase().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if (urlString.toLowerCase().contains(".zip")
                || urlString.toLowerCase().contains(".rar")) {
            // ZIP file
            intent.setDataAndType(uri, "application/trap");
        } else if (urlString.toLowerCase().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if (urlString.toLowerCase().contains(".wav")
                || urlString.toLowerCase().contains(".mp3")) {
            // WAV/MP3 audio file
            intent.setDataAndType(uri, "audio/*");
        } else if (urlString.toLowerCase().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if (urlString.toLowerCase().contains(".jpg")
                || urlString.toLowerCase().contains(".jpeg")
                || urlString.toLowerCase().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if (urlString.toLowerCase().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if (urlString.toLowerCase().contains(".3gp")
                || urlString.toLowerCase().contains(".mpg")
                || urlString.toLowerCase().contains(".mpeg")
                || urlString.toLowerCase().contains(".mpe")
                || urlString.toLowerCase().contains(".mp4")
                || urlString.toLowerCase().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            // if you want you can also define the intent type for any other file

            // additionally use else clause below, to manage other unknown extensions
            // in this case, Android will show all applications installed on the device
            // so you can choose which application to use
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * Return Extension of given path without dot(.)
     *
     * @param path
     * @return
     */
    public static String getExtension(String path) {
        return path.contains(".") ? path.substring(path.lastIndexOf(".") + 1).toLowerCase() : "";
    }
}
