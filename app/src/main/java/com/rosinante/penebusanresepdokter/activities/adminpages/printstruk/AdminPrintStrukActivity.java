package com.rosinante.penebusanresepdokter.activities.adminpages.printstruk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.printstruk.admindetailstruklistpage.AdminDetailStrukListActivity;
import com.rosinante.penebusanresepdokter.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminPrintStrukActivity extends AppCompatActivity {

    @BindView(R.id.edit_id_struk_print)
    EditText editIdStrukPrint;
    @BindView(R.id.edit_text_tanggal_pembayaran_print)
    EditText editTextTanggalPembayaranPrint;
    @BindView(R.id.edit_text_nama_lengkap_print)
    EditText editTextNamaLengkapPrint;
    @BindView(R.id.edit_text_nama_dokter_print)
    EditText editTextNamaDokterPrint;
    @BindView(R.id.edit_text_nama_poliklinik_print)
    EditText editTextNamaPoliklinikPrint;
    @BindView(R.id.edit_text_keterangan_resep_print)
    EditText editTextKeteranganResepPrint;
    @BindView(R.id.edit_text_nama_obat_print)
    EditText editTextNamaObatPrint;
    @BindView(R.id.edit_text_dosis_obat_print)
    EditText editTextDosisObatPrint;
    @BindView(R.id.edit_text_harga_obat_print)
    EditText editTextHargaObatPrint;
    @BindView(R.id.edit_text_total_harga_print)
    EditText editTextTotalHargaPrint;
    @BindView(R.id.edit_text_uang_bayar_print)
    EditText editTextUangBayarPrint;
    @BindView(R.id.edit_text_uang_kembalian_print)
    EditText editTextUangKembalianPrint;
    @BindView(R.id.cardregister)
    CardView cardregister;

    private static final int STORAGE_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_print_struk);
        ButterKnife.bind(this);
        String detail_obat_id = String.valueOf(getIntent().getIntExtra("detail_obat_id", 0));
        String tanggal_pembayaran = getIntent().getStringExtra("tanggal_pembayaran");
        String nama_pasien = getIntent().getStringExtra("nama_pasien");
        String nama_dokter = getIntent().getStringExtra("nama_dokter");
        String nama_poliklinik = getIntent().getStringExtra("nama_poliklinik");
        String resep_text = getIntent().getStringExtra("resep_text");
        String nama_obat = getIntent().getStringExtra("nama_obat");
        String dosis_obat = getIntent().getStringExtra("dosis_obat");
        String harga_obat = String.valueOf(getIntent().getDoubleExtra("harga_obat", 0));
        String total_harga = String.valueOf(getIntent().getDoubleExtra("total_harga", 0));
        String pembayaran = String.valueOf(getIntent().getDoubleExtra("pembayaran", 0));
        String kembalian = String.valueOf(getIntent().getDoubleExtra("kembalian", 0));

        editIdStrukPrint.setText(detail_obat_id);
        editTextTanggalPembayaranPrint.setText(tanggal_pembayaran);
        editTextNamaLengkapPrint.setText(nama_pasien);
        editTextNamaDokterPrint.setText(nama_dokter);
        editTextNamaPoliklinikPrint.setText(nama_poliklinik);
        editTextKeteranganResepPrint.setText(resep_text);
        editTextNamaObatPrint.setText(nama_obat);
        editTextDosisObatPrint.setText(dosis_obat);
        editTextHargaObatPrint.setText(harga_obat);
        editTextTotalHargaPrint.setText(total_harga);
        editTextUangBayarPrint.setText(pembayaran);
        editTextUangKembalianPrint.setText(kembalian);
    }

    public void createPdf(View view) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, STORAGE_CODE);
            } else {
                createAndSavePDF();
            }
        } else {
            createAndSavePDF();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createAndSavePDF();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminPrintStrukActivity.this, AdminDetailStrukListActivity.class));
        finish();
    }

    private void createAndSavePDF() {
        String IDStruk = editIdStrukPrint.getText().toString();
        String tanggalPembayaran = editTextTanggalPembayaranPrint.getText().toString();
        String namaPasien = editTextNamaLengkapPrint.getText().toString();
        String namaDokter = editTextNamaDokterPrint.getText().toString();
        String namaPoliklinik = editTextNamaPoliklinikPrint.getText().toString();
        String keteranganResep = editTextKeteranganResepPrint.getText().toString();
        String namaObat = editTextNamaObatPrint.getText().toString();
        String dosisObat = editTextDosisObatPrint.getText().toString();
        String hargaObat = editTextHargaObatPrint.getText().toString();
        String totalHarga = editTextTotalHargaPrint.getText().toString();
        double dokter_tarif = Double.parseDouble(getIntent().getStringExtra("dokter_tarif"));
        String uangBayar = editTextUangBayarPrint.getText().toString();
        String uangKembalian = editTextUangKembalianPrint.getText().toString();
        if (
                IDStruk.isEmpty()
                        && tanggalPembayaran.isEmpty()
                        && namaPasien.isEmpty()
                        && namaDokter.isEmpty()
                        && namaPoliklinik.isEmpty()
                        && keteranganResep.isEmpty()
                        && namaObat.isEmpty()
                        && dosisObat.isEmpty()
                        && hargaObat.isEmpty()
                        && totalHarga.isEmpty()
                        && uangBayar.isEmpty()
                        && uangKembalian.isEmpty()
        ) {
            Toast.makeText(this, "Data Kosong!", Toast.LENGTH_SHORT).show();
        } else {
            Document document = new Document();
            String fileName = "#DRX0" + IDStruk + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
            String filePath = Environment.getExternalStorageDirectory() + "/" + fileName + ".pdf";
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                //Document Settings
                document.setPageSize(PageSize.A7);
                document.addCreationDate();
                document.addAuthor("Klinik Sehat Selalu");

                //Set text color and text size
                BaseColor baseColor = new BaseColor(0, 153, 204, 255);
                float headingFontSize = 10.0f;
                float valueFontSize = 12.0f;
                //Set font
                BaseFont baseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
                //Create Line Separator
                LineSeparator lineSeparator = new LineSeparator();
                lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));

                //Add Resep Title
                Font resepTitleFont = new Font(baseFont, 24.0f, Font.NORMAL, BaseColor.BLACK);
                Chunk resepTitleChunk = new Chunk("Struk Resep", resepTitleFont);
                Paragraph resepTitleParagraph = new Paragraph(resepTitleChunk);
                resepTitleParagraph.setAlignment(Element.ALIGN_CENTER);
                document.add(resepTitleParagraph);

                //Add No. Resep section
                Font resepNoFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepNoChunk = new Chunk("No. Resep: ", resepNoFont);
                Paragraph resepNoParagraph = new Paragraph(resepNoChunk);
                document.add(resepNoParagraph);

                //Add No. Resep value
                Font resepNoValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepNoValueChunk = new Chunk("#RESEP0" + IDStruk, resepNoValueFont);
                Paragraph resepNoValueParagraph = new Paragraph(resepNoValueChunk);
                document.add(resepNoValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Tanggal Resep section
                Font resepDateFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepDateChunk = new Chunk("Tanggal Resep: ", resepDateFont);
                Paragraph resepDateParagraph = new Paragraph(resepDateChunk);
                document.add(resepDateParagraph);

                //Add Tanggal Resep value
                Font resepDateValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepDateValueChunk = new Chunk(tanggalPembayaran, resepDateValueFont);
                Paragraph resepDateValueParagraph = new Paragraph(resepDateValueChunk);
                document.add(resepDateValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Nama Pasien section
                Font NamaPasienFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk NamaPasienChunk = new Chunk("Nama Pasien: ", NamaPasienFont);
                Paragraph NamaPasienParagraph = new Paragraph(NamaPasienChunk);
                document.add(NamaPasienParagraph);

                //Add Nama Pasien value
                Font pasienNameValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk pasienNameValueChunk = new Chunk(namaPasien, pasienNameValueFont);
                Paragraph pasienNameValueParagraph = new Paragraph(pasienNameValueChunk);
                document.add(pasienNameValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Nama Dokter section
                Font NamaDokterFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk NamaDokterChunk = new Chunk("Nama Dokter: ", NamaDokterFont);
                Paragraph NamaDokterParagraph = new Paragraph(NamaDokterChunk);
                document.add(NamaDokterParagraph);

                //Add Nama Dokter value
                Font dokterNameValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk dokterNameValueChunk = new Chunk(namaDokter, dokterNameValueFont);
                Paragraph dokterNameValueParagraph = new Paragraph(dokterNameValueChunk);
                document.add(dokterNameValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));


                //Add Nama Poliklinik section
                Font NamaPoliklinikFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk NamaPoliklinikChunk = new Chunk("Nama Poliklinik: ", NamaPoliklinikFont);
                Paragraph NamaPoliklinikParagraph = new Paragraph(NamaPoliklinikChunk);
                document.add(NamaPoliklinikParagraph);

                //Add Nama Poliklinik value
                Font poliklinikNameValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk poliklinikNameValueChunk = new Chunk(namaPoliklinik, poliklinikNameValueFont);
                Paragraph poliklinikNameValueParagraph = new Paragraph(poliklinikNameValueChunk);
                document.add(poliklinikNameValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));


                //Add Keterangan Resep section
                Font KeteranganResepFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk KeteranganResepChunk = new Chunk("Keterangan Resep: ", KeteranganResepFont);
                Paragraph KeteranganResepParagraph = new Paragraph(KeteranganResepChunk);
                document.add(KeteranganResepParagraph);

                //Add Keterangan Resep value
                Font resepTextValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepTextValueChunk = new Chunk(keteranganResep, resepTextValueFont);
                Paragraph resepTextValueParagraph = new Paragraph(resepTextValueChunk);
                document.add(resepTextValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Nama Obat section
                Font resepNamaObatFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepNamaObatChunk = new Chunk("Nama Obat: ", resepNamaObatFont);
                Paragraph resepNamaObatParagraph = new Paragraph(resepNamaObatChunk);
                document.add(resepNamaObatParagraph);

                //Add Nama Obat value
                Font resepNamaObatValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepNamaObatValueChunk = new Chunk(namaObat, resepNamaObatValueFont);
                Paragraph resepNamaObatValueParagraph = new Paragraph(resepNamaObatValueChunk);
                document.add(resepNamaObatValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Dosis Obat section
                Font resepDosisObatFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepDosisObatChunk = new Chunk("Dosis Obat: ", resepDosisObatFont);
                Paragraph resepDosisObatParagraph = new Paragraph(resepDosisObatChunk);
                document.add(resepDosisObatParagraph);

                //Add Dosis Obat value
                Font resepDosisObatValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepDosisObatValueChunk = new Chunk(dosisObat, resepDosisObatValueFont);
                Paragraph resepDosisObatValueParagraph = new Paragraph(resepDosisObatValueChunk);
                document.add(resepDosisObatValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Harga Obat section
                Font resepHargaObatFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepHargaObatChunk = new Chunk("Harga Obat: ", resepHargaObatFont);
                Paragraph resepHargaObatParagraph = new Paragraph(resepHargaObatChunk);
                document.add(resepHargaObatParagraph);

                //Add Harga Obat value
                Font resepHargaObatValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepHargaObatValueChunk = new Chunk("Rp." + hargaObat + "/gr", resepHargaObatValueFont);
                Paragraph resepHargaObatValueParagraph = new Paragraph(resepHargaObatValueChunk);
                document.add(resepHargaObatValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Tarif Dokter section
                Font resepTarifDokterFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepTarifDokterChunk = new Chunk("Tarif Dokter: ", resepTarifDokterFont);
                Paragraph resepTarifDokterParagraph = new Paragraph(resepTarifDokterChunk);
                document.add(resepTarifDokterParagraph);

                //Add Tarif Dokter value
                Font resepTarifDokterValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepTarifDokterValueChunk = new Chunk("Rp." + dokter_tarif, resepTarifDokterValueFont);
                Paragraph resepTarifDokterValueParagraph = new Paragraph(resepTarifDokterValueChunk);
                document.add(resepTarifDokterValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Total Harga section
                Font resepTotalHargaFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepTotalHargaChunk = new Chunk("Total Harga: ", resepTotalHargaFont);
                Paragraph resepTotalHargaParagraph = new Paragraph(resepTotalHargaChunk);
                document.add(resepTotalHargaParagraph);

                //Add Total Harga value
                Font resepTotalHargaValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepTotalHargaValueChunk = new Chunk("Rp." + (Double.parseDouble(totalHarga) + dokter_tarif), resepTotalHargaValueFont);
                Paragraph resepTotalHargaValueParagraph = new Paragraph(resepTotalHargaValueChunk);
                document.add(resepTotalHargaValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Uang Pembayaran section
                Font resepPembayaranFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepPembayaranChunk = new Chunk("Uang Pembayaran: ", resepPembayaranFont);
                Paragraph resepPembayaranParagraph = new Paragraph(resepPembayaranChunk);
                document.add(resepPembayaranParagraph);

                //Add Uang Pembayaran value
                Font resepPembayaranValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepPembayaranValueChunk = new Chunk("Rp." + uangBayar, resepPembayaranValueFont);
                Paragraph resepPembayaranValueParagraph = new Paragraph(resepPembayaranValueChunk);
                document.add(resepPembayaranValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Add Uang Kembalian section
                Font resepKembalianFont = new Font(baseFont, headingFontSize, Font.NORMAL, baseColor);
                Chunk resepKembalianChunk = new Chunk("Uang Kembalian: ", resepKembalianFont);
                Paragraph resepKembalianParagraph = new Paragraph(resepKembalianChunk);
                document.add(resepKembalianParagraph);

                //Add Uang Kembalian value
                Font resepKembalianValueFont = new Font(baseFont, valueFontSize, Font.NORMAL, BaseColor.BLACK);
                Chunk resepKembalianValueChunk = new Chunk("Rp." + uangKembalian, resepKembalianValueFont);
                Paragraph resepKembalianValueParagraph = new Paragraph(resepKembalianValueChunk);
                document.add(resepKembalianValueParagraph);

                //Add line breakable space
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));

                //Close PDF Process
                document.close();
                FileUtils.openFile(AdminPrintStrukActivity.this, new File(filePath));
                Toast.makeText(this, fileName + " Telah tersimpan di " + filePath + "!", Toast.LENGTH_LONG).show();

            } catch (DocumentException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
