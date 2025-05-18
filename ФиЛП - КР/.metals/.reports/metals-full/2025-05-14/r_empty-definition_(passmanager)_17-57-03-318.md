error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala:
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 10
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
text:
```scala
// package@@ core

// import javax.crypto.Cipher
// import javax.crypto.spec.SecretKeySpec
// import java.util.Base64

// object Crypto {
//   // Мастер-пароль для шифрования/дешифрования
//   private val masterPassword = "freez-fox-fox-fox-2198391823" // Замените на запрос у пользователя
//   private val key = masterPassword.take(16).getBytes("UTF-8") // AES требует ключ длиной 16, 24 или 32 байта
//   private val cipher = Cipher.getInstance("AES")

//   // Шифрование
//   def encrypt(value: String): String = {
//     val keySpec = new SecretKeySpec(key, "AES")
//     cipher.init(Cipher.ENCRYPT_MODE, keySpec)
//     Base64.getEncoder.encodeToString(cipher.doFinal(value.getBytes("UTF-8")))
//   }

//   // Дешифрование
//   def decrypt(encryptedValue: String): String = {
//     val keySpec = new SecretKeySpec(key, "AES")
//     cipher.init(Cipher.DECRYPT_MODE, keySpec)
//     new String(cipher.doFinal(Base64.getDecoder.decode(encryptedValue)), "UTF-8")
//   }
// }

package core

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

object Crypto {
  private var key: Array[Byte] = Array.emptyByteArray
  private val cipher = Cipher.getInstance("AES")

  // Устанавливаем мастер-пароль
  def setMasterPassword(masterPassword: String): Unit = {
    // Дополняем мастер-пароль до 16 байт (AES-128)
    val rawKey = masterPassword.getBytes("UTF-8")
    val paddedKey = new Array[Byte](16)
    System.arraycopy(rawKey, 0, paddedKey, 0, math.min(rawKey.length, 16))
    key = paddedKey
  }

  // Шифрование
  def encrypt(value: String): String = {
    if (key.isEmpty) throw new IllegalStateException("Мастер-пароль не установлен")
    val keySpec = new SecretKeySpec(key, "AES")
    cipher.init(Cipher.ENCRYPT_MODE, keySpec)
    Base64.getEncoder.encodeToString(cipher.doFinal(value.getBytes("UTF-8")))
  }

  // Дешифрование
  def decrypt(encryptedValue: String): String = {
    if (key.isEmpty) throw new IllegalStateException("Мастер-пароль не установлен")
    val keySpec = new SecretKeySpec(key, "AES")
    cipher.init(Cipher.DECRYPT_MODE, keySpec)
    new String(cipher.doFinal(Base64.getDecoder.decode(encryptedValue)), "UTF-8")
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: 