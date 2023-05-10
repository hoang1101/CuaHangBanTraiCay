package com.example.cuahangbantraicay.activity;

import static android.media.MediaRecorder.VideoSource.CAMERA;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.API.ProductApi;
import com.example.cuahangbantraicay.Fragment.managerProduct;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;


public class ManagerProductCreate extends AppCompatActivity {

    managerProduct managerproduct = null;
    private int GALLERY_REQ_CODE = 1000;
    private EditText edtName, edtGiaNhap, edtGiaBan, edtContent, edtDiscount, edtSoLuong, edtSLCon;
    private Spinner spLoai;
    private ImageView edtImage;
    String imgPath = "";
    int idCategory;
    TextView btnSave, btnThoat;
    Bitmap bitmap = null;

    private Product product;
    ArrayAdapter arrayCatergory;
    int categoryCurrent = 0;

    // moi them 17/4
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;


    List<Category> ListCategory = new ArrayList<>();
    ProgressBar progressBar;
    static Boolean isActive = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manager_create_product);
        initCategory();

        setControl();


        setEvent();


    }

    private void initCategory() {
        try {
            CategoryApi.getCategory(getApplicationContext(), BASE_URL.BASE_URL + "all-category", new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    JSONArray data = result.getJSONArray("data");
                    JSONObject categoryObj = new JSONObject();
                    for (int i = 0; i < data.length(); i++) {
                        categoryObj = (JSONObject) data.get(i);
                        Category categoryTmp = new Category();
                        categoryTmp.setId(categoryObj.getInt("id"));
                        categoryTmp.setName(categoryObj.getString("name"));
                        ListCategory.add(categoryTmp);
                    }
                    setDataCategory();

                }

                @Override
                public void onError(VolleyError errorMessage) {

                }
            });
        } catch (JarException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDataCategory() {


        List<String> listtmp = new ArrayList<>();

//        System.out.println(ListCategory.get(0).getName());
        for (int i = 0; i < ListCategory.size(); i++) {
            listtmp.add(ListCategory.get(i).getName());


        }

        arrayCatergory = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listtmp);
        spLoai.setAdapter(arrayCatergory);
    }


    private void setControl() {
        edtImage = findViewById(R.id.PD_image_create);
        edtName = findViewById(R.id.PD_name_create);
        edtGiaNhap = findViewById(R.id.PD_price_in_create);
        edtGiaBan = findViewById(R.id.PD_price_sell_create);
        edtContent = findViewById(R.id.PD_content_create);
        spLoai = findViewById(R.id.PD_category_id_create);
        edtDiscount = findViewById(R.id.PD_discout_create);
        edtSoLuong = findViewById(R.id.PD_quantity_create);
        edtSLCon = findViewById(R.id.PD_quantity_sold_create);
        btnSave = findViewById(R.id.save_MPD_create);
        btnThoat = findViewById(R.id.exit_MPD);


    }

    //    public String getImagePath(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            String imagePath = cursor.getString(columnIndex);
//            cursor.close();
//            return imagePath;
//        }
//        return null;
//    }
//
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY_REQ_CODE) {
            if (data != null) {
                Uri contentURI = data.getData();
//                imgPath = getImagePath(contentURI);
//                System.out.println(contentURI);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    edtImage.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            edtImage.setImageBitmap(bitmap);
        }
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//            imageUri = data.getData();
//            Picasso.get().load(imageUri).into(edtImage);
//        }
    }


    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQ_CODE);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Vui lòng chọn !!!");
        String[] pictureDialogItems = {
                "Chọn ảnh từ Thư Viện",
                "Chụp ảnh bằng Camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
//
//    // convert ve 64bit
//    public static String CovertBitmapToBase64(Bitmap bitmap){
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        return  Base64.encodeToString(bytes, Base64.NO_WRAP);
//    }

    public static String CovertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    private void SaveCreate() {


        //call api create product
        System.out.println("nút lưu đc nhấn");
        Product productTmp = new Product();
        productTmp.setName(String.valueOf(edtName.getText()));
        productTmp.setPrice_in((float) Double.parseDouble(String.valueOf(edtGiaNhap.getText())));
        productTmp.setPrice_sell((float) Double.parseDouble(String.valueOf(edtGiaBan.getText())));
        productTmp.setContent(String.valueOf(edtContent.getText()));
        productTmp.setCategory_id(idCategory);
        productTmp.setDiscount(Integer.parseInt(String.valueOf(edtDiscount.getText())));
        productTmp.setQuantity(Integer.parseInt(String.valueOf(edtSoLuong.getText())));
        productTmp.setQuantity_sold(Integer.parseInt(String.valueOf(edtSLCon.getText())));
        String base64Img = CovertBitmapToBase64(bitmap);


        try {
            ProductApi.createProduct(getApplicationContext(), BASE_URL.BASE_URL + "create-product", productTmp, base64Img, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    System.err.println(errorMessage.getMessage());
//
                }
            });
        } catch (JSONException e) {
//

            throw new RuntimeException(e);

        }
    }



    private void CallAPIGetIdByName(String name) throws JSONException {
        ProductApi.getByName(ManagerProductCreate.this, BASE_URL.BASE_URL + "getbyname/" + name, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                System.out.println(result);
                idCategory = result.getInt("data");

            }

            @Override
            public void onError(VolleyError errorMessage) {

            }
        });
    }


    private void setEvent() {
        spLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                try {
                    CallAPIGetIdByName(selectedItem);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
        edtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isActive = true;
                Intent intent = new Intent(ManagerProductCreate.this, Admin.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveCreate();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isActive = true;
                        Intent intent = new Intent(ManagerProductCreate.this, Admin.class);
                        startActivity(intent);
                    }
                },3000);



            }
        });
    }
}
