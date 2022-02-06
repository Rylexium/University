package com.example.vkr.support_class;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.Optional;

import javax.crypto.spec.OAEPParameterSpec;

public class SelectImageClass {
    public final static int CAMERA = 0, GALLERY = 1;

    public static void showMenu(@NonNull AppCompatActivity activity, @NonNull Boolean multitouch) {

        final CharSequence[] options = { "Камера", "Галерея", "Отмена" };
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Добавить фото");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Камера")) {
                activity.startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA);
            }
            else if (options[item].equals("Галерея")) {
                    activity.startActivityForResult(multitouch ?
                            Intent.createChooser(new Intent()
                                    .putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                                    .setType("image/*")
                                    .setAction(Intent.ACTION_GET_CONTENT), "Выберите фотографии")
                            :
                            new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY);
            }
            else if (options[item].equals("Отмена")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
