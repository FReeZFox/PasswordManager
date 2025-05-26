error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala:core/`<import>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
empty definition using pc, found symbol in pc: core/`<import>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 16
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
text:
```scala
package core

im@@port javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64
import scala.util.Try

// object Crypto {
//   private val key: Array[Byte] = 
//     "my-default-key-123"
//     .getBytes("UTF-8")
//     .take(16)

//   private val cipher = Cipher.getInstance("AES")

//   private def withCipher(mode: Int, value: Array[Byte]): Array[Byte] = {
//     val keySpec = new SecretKeySpec(key, "AES")
//     cipher.init(mode, keySpec)
//     cipher.doFinal(value)
//   }

//   def encrypt(value: String): String =
//     Base64
//     .getEncoder
//     .encodeToString(
//       withCipher(
//         Cipher.ENCRYPT_MODE, 
//         value.getBytes("UTF-8")
//       )
//     )

//   def decrypt(encryptedValue: String): String =
//     new String(
//       withCipher(
//         Cipher.DECRYPT_MODE, 
//         Base64
//         .getDecoder
//         .decode(encryptedValue)
//       ), "UTF-8"
//     )
// }

trait Crypto[F[_]] {
  def encrypt(value: String): F[String]
  def decrypt(encryptedValue: String): F[String]
}

// Реализация для Try (совместимость со старым кодом)
class TryCrypto extends Crypto[Try] {
  import javax.crypto.Cipher
  import javax.crypto.spec.SecretKeySpec
  import java.util.Base64
  
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

  def encrypt(value: String): Try[String] = Try {
    Base64.getEncoder.encodeToString(
      withCipher(Cipher.ENCRYPT_MODE, value.getBytes("UTF-8"))
    )
  }

  def decrypt(encryptedValue: String): Try[String] = Try {
    new String(
      withCipher(
        Cipher.DECRYPT_MODE, 
        Base64.getDecoder.decode(encryptedValue)
      ), 
      "UTF-8"
    )
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: core/`<import>`.