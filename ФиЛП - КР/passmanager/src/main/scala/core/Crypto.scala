package core

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

object Crypto {
  private val key: Array[Byte] = 
    "my-default-key-123"
    .getBytes("UTF-8")
    .take(16)

  private val cipher = Cipher.getInstance("AES")

  private def withCipher(mode: Int, value: Array[Byte]): Array[Byte] = {
    val keySpec = new SecretKeySpec(key, "AES")
    cipher.init(mode, keySpec)
    cipher.doFinal(value)
  }

  def encrypt(value: String): String =
    Base64
    .getEncoder
    .encodeToString(
      withCipher(
        Cipher.ENCRYPT_MODE, 
        value.getBytes("UTF-8")
      )
    )

  def decrypt(encryptedValue: String): String =
    new String(
      withCipher(
        Cipher.DECRYPT_MODE, 
        Base64
        .getDecoder
        .decode(encryptedValue)
      ), "UTF-8"
    )
}


