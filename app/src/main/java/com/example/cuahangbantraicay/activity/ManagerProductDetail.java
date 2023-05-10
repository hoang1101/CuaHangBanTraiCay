package com.example.cuahangbantraicay.activity;

import static android.media.MediaRecorder.VideoSource.CAMERA;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.API.ProductApi;
import com.example.cuahangbantraicay.Fragment.managerCategory;
import com.example.cuahangbantraicay.Fragment.managerProduct;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

public class ManagerProductDetail extends AppCompatActivity {

    managerProduct managerproduct = null;
    private int GALLERY_REQ_CODE = 1000;
    private EditText edtName, edtGiaNhap, edtGiaBan, edtContent, edtDiscount, edtSoLuong, edtSLCon;
    private Spinner spLoai;
    private ImageView edtImage;

    TextView btnSave, btnThoat;
    Bitmap bitmap = null;

    private Product product;

    int idCategory;

    ArrayAdapter arrayCatergory;
    int categoryCurrent = 0;

    List<Category> ListCategory = new ArrayList<>();


    //    ProgressBar progressBar;
    static Boolean isActive = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        setControl();
        initCategory();
        product = (Product) getIntent().getSerializableExtra("pd");
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
//                        System.out.println(categoryTmp);

                    }
                    setData();

                }

                @Override
                public void onError(VolleyError errorMessage) {

                }
            });
        } catch (JarException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData() {
        Glide.with(this).load(product.getImage()).into(edtImage);
        System.out.println(product.getImage());
        edtName.setText(product.getName());
        edtGiaNhap.setText(String.valueOf(product.getPrice_in()));
        edtGiaBan.setText(String.valueOf(product.getPrice_sell()));
        edtContent.setText(product.getContent());
        List<String> listtmp = new ArrayList<>();
        for (int i = 0; i < ListCategory.size(); i++) {
            listtmp.add(ListCategory.get(i).getName());
        }
        arrayCatergory = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listtmp);
        spLoai.setAdapter(arrayCatergory);
        edtDiscount.setText(String.valueOf(product.getDiscount()));
        edtSoLuong.setText(String.valueOf(product.getQuantity()));
        edtSLCon.setText(String.valueOf(product.getQuantity()));
    }


    private void setControl() {
        edtImage = findViewById(R.id.PD_image);

        edtName = findViewById(R.id.PD_name);
        edtGiaNhap = findViewById(R.id.PD_price_in);
        edtGiaBan = findViewById(R.id.PD_price_sell);
        edtContent = findViewById(R.id.PD_content);
        spLoai = findViewById(R.id.PD_category_id);
        edtDiscount = findViewById(R.id.PD_discout);
        edtSoLuong = findViewById(R.id.PD_quantity);
        edtSLCon = findViewById(R.id.PD_quantity_sold);
        btnSave = findViewById(R.id.save_MPD);
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
    public static String CovertBitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return  Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    private void SaveProduct() {

//        System.out.println(product.getImage());
        product.setResourceId(product.getResourceId());
        product.setName(String.valueOf(edtName.getText()));
        product.setPrice_in((float) Double.parseDouble(String.valueOf(edtGiaNhap.getText())));
        product.setPrice_sell((float) Double.parseDouble(String.valueOf(edtGiaBan.getText())));
        product.setContent(String.valueOf(edtContent.getText()));
        product.setCategory_id(idCategory);
        product.setDiscount(Integer.parseInt(String.valueOf(edtDiscount.getText())));
        product.setQuantity(Integer.parseInt(String.valueOf(edtSoLuong.getText())));
        product.setQuantity_sold(Integer.parseInt(String.valueOf(edtSLCon.getText())));

        String base64Img="";
        if (bitmap == null){
            base64Img = "";
            System.out.println(base64Img);
        } else {
            base64Img = CovertBitmapToBase64(bitmap);
        }
        System.out.println(base64Img);
//        progressBar.setVi     sibility(View.VISIBLE);

        try {
            ProductApi.EditProduct(getApplicationContext(), BASE_URL.BASE_URL + "edit-product/" + product.getResourceId(), product,base64Img, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Toast.makeText(ManagerProductDetail.this, "thanhcong", Toast.LENGTH_SHORT).show();
                    isActive = true;
                    Intent intent = new Intent(ManagerProductDetail.this, Admin.class);
                    startActivity(intent);
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    Toast.makeText(ManagerProductDetail.this, "Thatbai", Toast.LENGTH_SHORT).show();

                }


            });
        } catch (JSONException e) {
            throw new RuntimeException(e);

        }
    }

    private void CallAPIGetIdByName(String name) throws JSONException {
        ProductApi.getByName(ManagerProductDetail.this, BASE_URL.BASE_URL + "api/admin/getbyname/"+name, new VolleyCallback() {
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
                    System.out.println(idCategory+"id ne haong ngu");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
//        System.out.println(selectionPosition+"nammmm");
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isActive = true;
                Intent intent = new Intent(ManagerProductDetail.this, Admin.class);
                startActivity(intent);
            }
        });
        edtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveProduct();
//                isActive= true;
//                Intent intent = new Intent(ManagerProductDetail.this, Admin.class);
//                startActivity(intent);
            }
        });
    }
}
