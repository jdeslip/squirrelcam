/*
    SquirrelCam - a Free Squirrel Webcam app for Android
    http://code.google.com/p/squirrelcam

    Copyright (C) 2010 Jack Deslippe

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

 */

package com.commonsware.android.SquirrelCam;

import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.content.Context;
import android.net.Uri;

/*
 * Tells the Android media-scanner to include screenshots in the library.
 * Based roughly on the thread at the android developer forum -
 * http://www.mail-archive.com/android-developers@googlegroups.com/msg24164.html
 * Assumed to be public domain.
 */

public class MediaScannerNotifier implements MediaScannerConnectionClient {

    private MediaScannerConnection mConnection;
    private String mPath;
    private String mMimeType;

    public MediaScannerNotifier(Context context, String path, String mimeType) {
        mPath = path;
        mMimeType = mimeType;
        mConnection = new MediaScannerConnection(context, this);
        mConnection.connect();
    }

    public void onMediaScannerConnected() {
        mConnection.scanFile(mPath, mMimeType);
    }

    public void onScanCompleted(String path, Uri uri) {
        mConnection.disconnect();
    }
}
