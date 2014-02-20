package com.dianping.zebra.group.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/**
 * AES Coder<br/>
 * secret key length: 128bit, default: 128 bit<br/>
 * mode: ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/>
 * padding: Nopadding/PKCS5Padding/ISO10126Padding/
 * 
 */
public class AESCoder {

	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 初始化密钥
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static Key generateSecretKey() throws NoSuchAlgorithmException {
		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(128);
		// 生成一个密钥
		return kg.generateKey();
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *           待加密数据
	 * @param key
	 *           密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException,
	      InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *           待加密数据
	 * @param key
	 *           密钥
	 * @param cipherAlgorithm
	 *           加密算法/工作模式/填充方式
	 * @return byte[] 加密数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *           待解密数据
	 * @param key
	 *           密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException,
	      InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *           待解密数据
	 * @param key
	 *           密钥
	 * @param cipherAlgorithm
	 *           加密算法/工作模式/填充方式
	 * @return byte[] 解密数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws NoSuchAlgorithmException,
	      NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 执行操作
		return cipher.doFinal(data);
	}

}
