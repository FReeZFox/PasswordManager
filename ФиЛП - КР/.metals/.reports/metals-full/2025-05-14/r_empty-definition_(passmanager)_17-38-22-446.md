error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 269
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
text:
```scala
package core

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

object Crypto {
  // Мастер-пароль для шифрования/дешифрования
  //private val masterPassword = "my-master-password" // Замените на запрос у пользователя
  private @@val key = masterPassword.take(16).getBytes("UTF-8") // AES требует ключ длиной 16, 24 или 32 байта
  private val cipher = Cipher.getInstance("AES")

  // Шифрование
  def encrypt(value: String): String = {
    val keySpec = new SecretKeySpec(key, "AES")
    cipher.init(Cipher.ENCRYPT_MODE, keySpec)
    Base64.getEncoder.encodeToString(cipher.doFinal(value.getBytes("UTF-8")))
  }

  // Дешифрование
  def decrypt(encryptedValue: String): String = {
    val keySpec = new SecretKeySpec(key, "AES")
    cipher.init(Cipher.DECRYPT_MODE, keySpec)
    new String(cipher.doFinal(Base64.getDecoder.decode(encryptedValue)), "UTF-8")
  }
}



```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.