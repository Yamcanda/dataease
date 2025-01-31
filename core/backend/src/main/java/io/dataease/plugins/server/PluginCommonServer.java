package io.dataease.plugins.server;

import io.dataease.commons.utils.LogUtil;
import io.dataease.commons.utils.ServletUtils;
import io.dataease.plugins.common.dto.PluginSysMenu;
import io.dataease.plugins.common.dto.StaticResource;
import io.dataease.plugins.common.service.PluginComponentService;
import io.dataease.plugins.common.service.PluginMenuService;
import io.dataease.plugins.common.util.SpringContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@ApiIgnore
@RestController
@RequestMapping("/api/pluginCommon")
public class PluginCommonServer {

    @GetMapping("/async/{menuId}")
    public void menuInfo(@PathVariable Long menuId) {
        Map<String, PluginMenuService> pluginMenuServiceMap = SpringContextUtil.getApplicationContext().getBeansOfType(PluginMenuService.class);
        pluginMenuServiceMap.values().stream().forEach(service -> {
            AtomicReference<PluginSysMenu> atomicReference = new AtomicReference<>();
            List<PluginSysMenu> menus = service.menus();
            if (menus.stream().anyMatch(menu -> {
                atomicReference.set(menu);
                return menu.getMenuId().equals(menuId);
            })) {
                String jsName = atomicReference.get().getComponent();
                HttpServletResponse response = ServletUtils.response();
                BufferedInputStream bis = null;
                InputStream inputStream = null;
                OutputStream os = null; //输出流
                try {
                    inputStream = service.vueResource(jsName);
                    byte[] buffer = new byte[1024];
                    os = response.getOutputStream();
                    bis = new BufferedInputStream(inputStream);
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                } catch (Exception e) {
                    LogUtil.error(e);
                } finally {
                    try {
                        if(bis != null) bis.close();
                        if(inputStream != null) inputStream.close();
                        if(os != null) os.close();
                    } catch (IOException ignore) {
                    }
                }
            }
            return;
        });
    }

    @GetMapping("/component/{componentName}")
    public void componentInfo(@PathVariable String componentName) {
        Map<String, PluginComponentService> beansOfType = SpringContextUtil.getApplicationContext().getBeansOfType(PluginComponentService.class);
        beansOfType.values().stream().forEach(service -> {
            List<String> components = service.components();
            if (components.contains(componentName)) {
                HttpServletResponse response = ServletUtils.response();
                BufferedInputStream bis = null;
                InputStream inputStream = null;
                OutputStream os = null; //输出流
                try {
                    inputStream = service.vueResource(componentName);
                    byte[] buffer = new byte[1024];
                    os = response.getOutputStream();
                    bis = new BufferedInputStream(inputStream);
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                } catch (Exception e) {
                    LogUtil.error(e);
                } finally {
                    try {
                        if(bis != null) bis.close();
                        if(inputStream != null) inputStream.close();
                        if(os != null) os.close();
                    } catch (IOException ignore) {
                    }
                }
                return;
            }
        });
    }

    @GetMapping("/staticInfo/{name}/{suffix}")
    public void staticInfo(@PathVariable("name") String name, @PathVariable("suffix") String suffix) {
        Map<String, PluginComponentService> beansOfType = SpringContextUtil.getApplicationContext().getBeansOfType(PluginComponentService.class);
        beansOfType.values().stream().forEach(service -> {
            List<StaticResource> staticResources = service.staticResources();

            if (staticResources.stream().anyMatch(resource -> resource.match(name, suffix))) {
                HttpServletResponse response = ServletUtils.response();
                BufferedInputStream bis = null;
                InputStream inputStream = null;
                OutputStream os = null; //输出流
                try {
                    LogUtil.info("name: " + name + ", suffix: " + suffix);
                    inputStream = service.vueResource(name, suffix);
                    byte[] buffer = new byte[1024];
                    os = response.getOutputStream();
                    bis = new BufferedInputStream(inputStream);
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    if (suffix.indexOf("svg") != -1)
                        response.setContentType("image/svg+xml");
                    if (suffix.indexOf("png") != -1)
                        response.setContentType("image/png");
                    os.flush();
                } catch (Exception e) {
                    LogUtil.error(e);
                } finally {
                    try {
                        if(bis != null) bis.close();
                        if(inputStream != null) inputStream.close();
                        if(os != null) os.close();
                    } catch (IOException ignore) {
                    }
                }
                return;
            }
        });
    }
}
