error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala:javax/crypto/spec/SecretKeySpec#
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
empty definition using pc, found symbol in pc: javax/crypto/spec/SecretKeySpec#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -javax/crypto/spec/SecretKeySpec#
	 -SecretKeySpec#
	 -scala/Predef.SecretKeySpec#
offset: 429
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/Crypto.scala
text:
```scala
package core

import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

trait CryptoAlgebra[F[_]] {
  def encrypt(value: String): F[String]
  def decrypt(value: String): F[String]
}

object CryptoIO {
  import scala.util.Try

  object TryCryptoInterpreter extends CryptoAlgebra[Try] {
    private val key = "1234567890123456" 
    private def getKeySpec = new S@@ecretKeySpec(key.getBytes("UTF-8"), "AES")

    override def encrypt(value: String): Try[String] = Try {
      val cipher = Cipher.getInstance("AES")
      cipher.init(Cipher.ENCRYPT_MODE, getKeySpec)

      val encrypted = cipher.doFinal(value.getBytes("UTF-8"))
      Base64.getEncoder.encodeToString(encrypted)
    }

    override def decrypt(value: String): Try[String] = Try {
      val cipher = Cipher.getInstance("AES")
      cipher.init(Cipher.DECRYPT_MODE, getKeySpec)

      val decoded = Base64.getDecoder.decode(value)
      new String(cipher.doFinal(decoded), "UTF-8")
    }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: javax/crypto/spec/SecretKeySpec#