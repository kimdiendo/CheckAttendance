package com.example.checkattendance.Models;


import android.content.Context;
import android.graphics.Bitmap;

import com.example.checkattendance.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FaceRecognizer {
    public FaceRecognizer() {

    }

    public Integer predict(Context context, Bitmap bitmap_image) {
        int predicted_class = 0;
        try {
            Model model = Model.newInstance(context);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 96, 96, 3}, DataType.FLOAT32);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap_image, 96, 96, false);
            // Convert the resized bitmap to a ByteBuffer
            ByteBuffer inputBuffer = ByteBuffer.allocateDirect(96 * 96 * 3 * 4); // Assuming 3 channels (RGB) and 4 bytes per float
            inputBuffer.order(ByteOrder.nativeOrder());
            inputBuffer.rewind();
            int[] pixels = new int[96 * 96];
            resizedBitmap.getPixels(pixels, 0, resizedBitmap.getWidth(), 0, 0, resizedBitmap.getWidth(), resizedBitmap.getHeight());
            for (int pixelValue : pixels) {
                // Convert the pixel values to floats and normalize them
                float r = ((pixelValue >> 16) & 0xFF) / 255.0f;
                float g = ((pixelValue >> 8) & 0xFF) / 255.0f;
                float b = (pixelValue & 0xFF) / 255.0f;
                inputBuffer.putFloat(r);
                inputBuffer.putFloat(g);
                inputBuffer.putFloat(b);
            }
            inputFeature0.loadBuffer(inputBuffer);
            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            float[] outputScoresArray = outputFeature0.getFloatArray();
            float maxScore = outputScoresArray[0];
            for (int i = 1; i < outputScoresArray.length; i++) {
                if (outputScoresArray[i] > maxScore) {
                    maxScore = outputScoresArray[i];
                    predicted_class = i;
                }
            }

            model.close();
        } catch (
                IOException e) {
            // TODO Handle the exception
        }
        return predicted_class;
    }

}
