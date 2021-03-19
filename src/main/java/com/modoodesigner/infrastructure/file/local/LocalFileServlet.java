package com.modoodesigner.infrastructure.file.local;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@WebServlet("/local-file/*")
@Slf4j
public class LocalFileServlet extends HttpServlet {

    private static final long serialVersionUID = -8951045829631649069L;

    private String localRootPath;
    private Environment environment;

    public LocalFileServlet(@Value("${app.file-storage.local-root-folder}") String localRootPath,
                            Environment environment) {
        this.localRootPath = localRootPath;
        this.environment = environment;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        List<String> localProfiles = Arrays.asList("local");
        if (!profiles.stream().filter(localProfiles::contains).findAny().isEmpty()) {
            log.warn("'{}' 환경에서 로컬파일 서블릿에 접근했습니다. IP 주소: '{}' ", String.join(",", profiles), request.getPathInfo());
        }

        String pathInfo = request.getPathInfo();
        if ("/".equals(pathInfo)) {
            response.getWriter().write("/");
            return;
        }

        String filePath = localRootPath + request.getPathInfo();
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            response.sendError(404);
            return;
        }

        response.setContentType(request.getServletContext().getMimeType(pathInfo));
        response.setHeader("Cache-Control", "public, max-age=31536000");
        Files.copy(Paths.get(localRootPath, pathInfo), response.getOutputStream());
    }
}
