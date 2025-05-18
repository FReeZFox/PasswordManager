error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordForm.scala:
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordForm.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 703
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordForm.scala
text:
```scala
// package core

// import scalafx.beans.property.StringProperty
// import models.PasswordEntry
// import scalafx.collections.ObservableBuffer

// object PasswordForm {
//   def validateAndAddEntry(
//     service: String,
//     username: String,
//     password: String,
//     entries: ObservableBuffer[PasswordEntry]
//   ): (Boolean, String) = {  // Возвращаем кортеж (успех, сообщение об ошибке)
    
//     if (service.isEmpty || username.isEmpty || password.isEmpty) {
//       return (false, "Все поля обязательны для заполнения.")
//     }

//     val exists = entries.exists { entry =>
//       entry.service.value.equalsIgnoreCase(service) &&
//       entry.username.valu@@e.equalsIgnoreCase(username) 
//     }

//     if (exists) {
//       (false, "Запись с таким логином и сервисом уже существует.")
//     } else {
//       entries += PasswordEntry(
//         StringProperty(service),
//         StringProperty(username),
//         StringProperty(password)
//       )
//       PasswordList.saveToStorage()
//       (true, "")
//     }
//   }

//   def deleteEntries(selected: Seq[PasswordEntry], entries: ObservableBuffer[PasswordEntry]): Unit = {
//     if (selected.nonEmpty) {
//       entries --= selected
//       PasswordList.saveToStorage()
//     }
//   }
// }

```


#### Short summary: 

empty definition using pc, found symbol in pc: 