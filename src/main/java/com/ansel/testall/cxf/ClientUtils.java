package com.ansel.testall.cxf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.util.ResourceUtils;

public class ClientUtils {
	private final static String keyStore = "client.store";

	private final static String trustStore = "client.store";

	private final static String trustStorePass = "letmein";

	private final static String keyStorePass = "letmein";
	
	public static KeyManager[] getKeyManagers() {
		InputStream is = null;
		try {
			// 获取默认的 X509算法
			String alg = KeyManagerFactory.getDefaultAlgorithm();
			// 创建密钥管理工厂
			KeyManagerFactory factory = KeyManagerFactory.getInstance(alg);
			File certFile = ResourceUtils.getFile("classpath:mykeys.jks");
			if (!certFile.exists() || !certFile.isFile()) {
				return null;
			}
			is = new FileInputStream(certFile);
			// 构建以证书相应格式的证书仓库
			KeyStore ks = KeyStore.getInstance("JKS");
			// 加载证书
			ks.load(is, trustStorePass.toCharArray());
			factory.init(ks, trustStorePass.toCharArray());
			KeyManager[] keyms = factory.getKeyManagers();
			return keyms;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return null;
	}

	public static TrustManager[] getTrustManagers() {
		// 读取证书仓库输入流
		InputStream is = null;
		try {
			// 信任仓库的默认算法X509
			String alg = TrustManagerFactory.getDefaultAlgorithm();
			// 获取信任仓库工厂
			TrustManagerFactory factory = TrustManagerFactory.getInstance(alg);
			// 读取信任仓库
			is = new FileInputStream(ResourceUtils.getFile("classpath:mykeys.jks"));
			// 密钥类型
			KeyStore ks = KeyStore.getInstance("JKS");
			// 加载密钥
			ks.load(is, keyStorePass.toCharArray());
			factory.init(ks);
			TrustManager[] tms = factory.getTrustManagers();
			return tms;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
