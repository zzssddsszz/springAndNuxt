package com.modoodesigner.utils;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.UserService;
import com.modoodesigner.domain.application.commands.AttachmentUploadCommand;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.application.commands.UserRegisterCommand;
import com.modoodesigner.domain.common.model.Role;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.domain.model.attachment.AttachmentRepository;
import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.pendant.MountingType;
import com.modoodesigner.domain.model.user.User;
import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.web.payload.PendantRegistrationPayload;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

@Component
@Profile({"local", "test"})
@RequiredArgsConstructor
public class LocalTestInit {
    private final InitMemberService initMemberService;
    private final InitPendantService initPendantService;
    private final InitProductService initProductService;

    @PostConstruct
    public void init() {
        initMemberService.init();
        initPendantService.init();
        initProductService.init();
    }

    @Component
    @Transactional
    @Slf4j
    static class InitMemberService {
        @Autowired
        UserService userService;

        /*@Autowired
        AttachmentRepository attachmentRepository;*/

        @SneakyThrows
        @Transactional
        public void init() {
            log.debug("------------------------------------------");
            log.debug("유저생성");
            String name = "user";
            UserRegisterCommand command = new UserRegisterCommand(name, name + "@test.com", "user");
            userService.register(command);
            command = new UserRegisterCommand("admin", "admin@test.com", "admin");
            userService.register(command);
            User admin = userService.findByUsername("admin");
            admin.addRoles(new Role("ROLE_ADMIN"));
            admin.addRoles(new Role("ACTUATOR_ADMIN"));
            log.debug("유저생성완료");
            log.debug("------------------------------------------");



           /* log.debug("------------------------------------------");
            log.debug("이미지 생성");
            List<Attachment> attachmentList = new ArrayList<>();
            for (int i = 1; i <= 50; i++) {
                Attachment attachment = Attachment.builder()
                        .user(userService.findById(new UserId(1L)))
                        .fileName("테스트 이미지 파일명 " +i )
                        .thumbnailCreated(true)
                        .publicFtp(true)
                        .filePath(i+"/test.jpg")
                        .build();
                attachmentList.add(attachment);
                attachmentRepository.save(attachment);
            }
            log.debug("------------------------------------------");*/
        }
    }


    @Component
    @Transactional
    @Slf4j
    static class InitPendantService {
        @Autowired
        PendantService pendantService;

        @Autowired
        UserService userService;


        @SneakyThrows
        @Transactional
        public void init() {
            log.debug("------------------------------------------");

            log.debug("펜던트 생성");
            for (int i = 0; i < 10; i++) {
                PendantRegisterCommand command = PendantRegisterCommand.builder()
                        .name("테스트펜던트" + i)
                        .buyPrice(1000)
                        .stock(100)
                        .color(PlatingColor.PINK)
                        .material(Material.SILVER)
                        .mountingType(MountingType.SINGLE)
                        .mainImages(Arrays.asList(1L, 2L, 3L, 4L, 5L))
                        .build();
                pendantService.register(command);
            }

            log.debug("------------------------------------------");
        }
    }

    @Component
    @Transactional
    @Slf4j
    static class InitProductService {
        @Autowired
        ProductService productService;

        @Autowired
        AttachmentRepository attachmentRepository;

        @Autowired
        UserService userService;


        @SneakyThrows
        @Transactional
        public void init() {

            log.debug("제품 생성");
            for (int i = 0; i < 50; i++) {
                Attachment attachment1 = Attachment.builder()
                        .filePath("filePath.jpg")
                        .fileName("이미지 이름")
                        .user(userService.findById(new UserId(1L)))
                        .thumbnailCreated(true)
                        .publicFtp(true)
                        .build();
                Attachment attachment2 = Attachment.builder()
                        .filePath("filePath2.jpg")
                        .fileName("이미지2 이름")
                        .user(userService.findById(new UserId(1L)))
                        .thumbnailCreated(true)
                        .publicFtp(true)
                        .build();

                Attachment save1 = attachmentRepository.save(attachment1);
                Attachment save2 = attachmentRepository.save(attachment2);
                ProductRegisterCommand command = ProductRegisterCommand.builder()
                        .name("테스트 상품" + i)
                        .content("테스트 컨텐츠" + i)
                        .mainImages(Arrays.asList(save1.getId().longValue(),save2.getId().longValue()))
                        .build();
                productService.register(command);
            }

            log.debug("------------------------------------------");
        }
    }
}
