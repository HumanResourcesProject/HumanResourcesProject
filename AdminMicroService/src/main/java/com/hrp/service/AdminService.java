package com.hrp.service;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.dto.response.FindAdminResponseDto;
import com.hrp.exception.AdminException;
import com.hrp.exception.EErrorType;
import com.hrp.repository.IAdminRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.utility.ServiceManagerImpl;
//import io.imagekit.sdk.ImageKit;
//import io.imagekit.sdk.config.Configuration;
//import io.imagekit.sdk.models.FileCreateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class AdminService extends ServiceManagerImpl<Admin, Long> {

    @Value("${dropbox.token}")
    private String drptoken;
    private final IAdminRepository adminRepository;


    public AdminService(IAdminRepository adminRepository) {
        super(adminRepository);
        this.adminRepository = adminRepository;
    }


    public Boolean createAdmin(CreateAdminRequestDto dto)  {

        save(Admin.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build());
        return true;

    }

    public FindAdminResponseDto findMe(Long adminId){
        if (adminId==null){
            throw new AdminException(EErrorType.BAD_REQUEST_ERROR,"id null geldi");
        }
        Admin admin = adminRepository.findById(adminId).get();
        return FindAdminResponseDto.builder()
                .email(admin.getEmail())
                .username(admin.getUsername())
                .name(admin.getName())
                .surname(admin.getSurname())
                .build();
    }


    public Boolean uploadImage(MultipartFile file) throws IOException {
        System.out.println("***********");
        try {
            // Dropbox'a bağlanmak için erişim anahtarlarınızı kullanın
            DbxRequestConfig config = new DbxRequestConfig("HRMProjectt");
            String token = drptoken;
            DbxClientV2 client = new DbxClientV2(config, token);
            System.out.println("***********2");

            // Dosyayı yükle
            String fileName = file.getOriginalFilename();
            System.out.println("***********3");
            InputStream inputStream = new BufferedInputStream(file.getInputStream());
            System.out.println("***********4" + inputStream);
            FileMetadata metadata = client.files().uploadBuilder("/MyFolder/" + fileName)
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(inputStream);
            System.out.println("***********5");

            // Yükleme başarılı olduysa, dosyanın Dropbox URL'sini döndürün
            String fileUrl = client.sharing().createSharedLinkWithSettings(metadata.getPathLower()).getUrl();
            /*
            Admin admin = new Admin();
            admin.setAvatar(fileUrl);
            update(admin);
             */
            System.out.println(fileUrl);

            return true;
        } catch (Exception e) {
            System.out.println("*********** Exception" + e.getMessage());
            e.printStackTrace();

            // Hata oluşursa, hata mesajını döndürün
            return false;
        }
    }
}
/**
 // Dosyayı Dropbox'a yükleyin ve paylaşılabilir bir URL elde edin
 String dropboxUrl = uploadFileToDropboxAndGetUrl(file);

 // Cloudfire'a yükleme işlemi için gerekli istek ayarlarını hazırlayın
 RestTemplate restTemplate = new RestTemplate();
 HttpHeaders headers = new HttpHeaders();
 headers.setContentType(MediaType.APPLICATION_JSON);
 headers.set("Authorization", "Bearer " + CLOUDINARY_API_SECRET);

 // Yükleme işlemi için gerekli istek gövdesini oluşturun
 String body = String.format("{\"source\":{\"type\":\"url\",\"url\":\"%s\"}}", dropboxUrl);

 // Cloudfire'a yükleme isteğini gönderin
 HttpEntity<String> request = new HttpEntity<>(body, headers);
 ResponseEntity<String> response = restTemplate.postForEntity(CLOUDINARY_UPLOAD_URL, request, String.class);

 return ResponseEntity.ok(response.getBody());
 }

 private String uploadFileToDropboxAndGetUrl(MultipartFile file) throws DbxException, IOException {
 // Dropbox API erişim anahtarlarını kullanarak dosyayı yükleyin ve paylaşılabilir bir URL elde edin
 // Burada yer alan kod örneği sadece referans amaçlıdır ve düzgün çalışmayabilir, Dropbox API dokümantasyonunu kullanarak kendi işlemlerinizi oluşturmanız gerekmektedir.
 DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", Locale.getDefault().toString());
 DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
 */
