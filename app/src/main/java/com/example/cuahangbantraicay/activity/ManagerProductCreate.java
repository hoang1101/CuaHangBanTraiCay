package com.example.cuahangbantraicay.activity;

import static android.media.MediaRecorder.VideoSource.CAMERA;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
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
import com.example.cuahangbantraicay.API.ProductApi;
import com.example.cuahangbantraicay.Fragment.managerProduct;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerProductCreate extends AppCompatActivity {

    managerProduct managerproduct = null;
    private int GALLERY_REQ_CODE = 1000;
    private EditText edtName, edtGiaNhap, edtGiaBan, edtContent, edtDiscount, edtSoLuong, edtSLCon;
    private Spinner edtLoai;
    private ImageView edtImage;

    TextView btnSave, btnThoat;
    Bitmap bitmap = null;

    private Product product;
    ArrayAdapter arrayCatrgory;
    int categoryCurrent = 0;

    List<String> ListCategory = new ArrayList<>();
    ProgressBar progressBar;
    static Boolean isActive = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manager_create_product);

        setControl();
//        product = (Product) getIntent().getSerializableExtra("pd");
//        System.out.println("hehehhe:"+product.getName());
//        setData();
        setEvent();


    }

//    private void setData() {
//        Glide.with(this).load(product.getImage()).into(edtImage);
//        edtName.setText(product.getName());
//        edtGiaNhap.setText(String.valueOf(product.getPrice_in()));
//        edtGiaBan.setText(String.valueOf(product.getPrice_sell()));
//        edtContent.setText(product.getContent());
////        edtLoai. = findViewById(R.id.PD_category_id);
//        edtDiscount.setText(String.valueOf(product.getDiscount()));
//        edtSoLuong.setText(String.valueOf(product.getQuantity()));
//        edtSLCon.setText(String.valueOf(product.getQuantity()));
//    }


    private void setControl() {
        edtImage = findViewById(R.id.PD_image_create);
        edtName = findViewById(R.id.PD_name_create);
        edtGiaNhap = findViewById(R.id.PD_price_in_create);
        edtGiaBan = findViewById(R.id.PD_price_sell_create);
        edtContent = findViewById(R.id.PD_content_create);
        edtLoai = findViewById(R.id.PD_category_id_create);
        edtDiscount = findViewById(R.id.PD_discout_create);
        edtSoLuong = findViewById(R.id.PD_quantity_create);
        edtSLCon = findViewById(R.id.PD_quantity_sold_create);
        btnSave = findViewById(R.id.save_MPD_create);
        btnThoat = findViewById(R.id.exit_MPD);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY_REQ_CODE) {
            if (data != null) {
                Uri contentURI = data.getData();
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

    // convert ve 64bit
    public static String CovertBitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return  Base64.encodeToString(bytes, Base64.NO_WRAP);
    }
    private void SaveCreate() {


        //call api create product
        System.out.println("nút lưu đc nhấn");
        Product productTmp = new Product();
        productTmp.setName(String.valueOf(edtName.getText()));
        productTmp.setPrice_in((float) Double.parseDouble(String.valueOf(edtGiaNhap.getText())));
        productTmp.setPrice_sell((float) Double.parseDouble(String.valueOf(edtGiaBan.getText())));
        productTmp.setContent(String.valueOf(edtContent.getText()));
//        productTmp.setCategory_id(productObj.getInt("category_id"));
        productTmp.setDiscount(Integer.parseInt(String.valueOf(edtDiscount.getText())));
        productTmp.setQuantity(Integer.parseInt(String.valueOf(edtSoLuong.getText())));
        productTmp.setQuantity_sold(Integer.parseInt(String.valueOf(edtSLCon.getText())));
//        progressBar.setVi     sibility(View.VISIBLE);

        String base64Img = CovertBitmapToBase64(bitmap);


        try {
            ProductApi.createProduct(getApplicationContext(), BASE_URL.BASE_URL + "api/admin/create-product", productTmp, base64Img, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//                                progressDialog.dismiss();

//                    progressBar.setVisibility(View.GONE);
//                    CustomToast.makeText(ManagerProductDetail.this, "Thêm Mới Sản Phẩm Thành Công", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS, true).show();
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    System.err.println(errorMessage.getMessage());
//                                progressDialog.dismiss();
//                    progressBar.setVisibility(View.GONE);
//                    CustomToast.makeText(ManagerProductDetail.this, "Error Thêm Mới Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();
                }
            });
        } catch (JSONException e) {
//                        progressDialog.dismiss();
//            progressBar.setVisibility(View.GONE);
//            CustomToast.makeText(ManagerProductDetail.this, "Catch Thêm Mới Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

            throw new RuntimeException(e);

        }
    }

    private void setEvent() {
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
            }
        });
    }
}
