package com.askonlinesolutions.wengage.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.askonlinesolutions.wengage.R;

public class FaceDetection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detection);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (!FacelyticsUtils.isInit())
//        {
//            FacelyticsCameraServiceNative newService = (FacelyticsCameraServiceNative) FacelyticsUtils.createInstance(
//                    new FacelyticsCameraServiceNative(FaceDetection.this, "config_file","22ASHDfhf6%"));
//            newService.setRenderToMat(true);
//            newService.load(KInputCamId.CAMERA_ID_FRONT);
//        }
//        final FacelyticsCameraServiceNative service = (FacelyticsCameraServiceNative) FacelyticsUtils.getInstance();


//        service.addOnEventListener(new OnFaceListener() {
//            @Override
//            public void onEvent(String rawEvent) throws JSONException {
//                super.onEvent(rawEvent);
//                // Do something...
//
//                // #1 - Retrieve basis information
//                // getEvent().getTimestamp() <-- Timestamp event
//                // getEvent().getFrameWidth() <-- captured frame width
//                // getEvent().getFrameHeight() <-- captured frame width
//                // getEvent().getFDetectTime() <-- Time to process a simple face detection
//                // getEvent().getFDetectNextTick() <-- Time to wait before the next face detection
//                // getEvent().getFDetectByPassed() <-- Amount of face detect thread by passed
//
//                // #2 - More information available with FacelyticsFaceEvent
//                // FacelyticsFaceEvent faceEvent = (FacelyticsFaceEvent) getEvent();
//
//                // #3 - Retrieve basis face information
//                // faceEvent.getFacesCount() <-- Detected faces count
//                // faceEvent.getFace(_index) <-- Return a Face object at specified index
//                // faceEvent.getFaceId(_index) <-- Return id of a face at specified index
//                // faceEvent.getFaces(); <-- Return an array of detected faces
//
//                // #4 - Retrieve advanced face information at specified index
//                // faceEvent.getPosition(_index)
//                // Eyes eyes = faceEvent.getEyes(_index)
//                // Age age = faceEvent.getAge(_index)
//                // Emotion emotion = faceEvent.getEmotion(_index)
//                // Gender gender = faceEvent.getGender(_index)
//                // Glass glass = faceEvent.getGlass(_index)
//                // Motion motion = faceEvent.getMotion(_index)
//            }
//        });
//
//        service.record(FaceDetection.this, (KFrameRender) findViewById(R.id.render), true, true);

    }
}
