package io.lambdaworks.workshop.exceptions

object PasswordChecker {

  def validate(password: String): Either[List[Throwable], String] = {
    val validations : List[ Either [ Throwable, String ]] = List(
      minNumberOfChars(password),
      containsUpperCase(password),
      containsLowerCase(password),
      containsNumber(password)
    )

    val (errors, _) = validations.partition(_.isLeft)

    val errorList = errors.collect { case Left(e) => e }

    if (errorList.isEmpty) Right(password)
    else Left(errorList)

  }

  private def minNumberOfChars(password: String, length: Int = 5): Either[Throwable, String] = {
    if(password.length() < length) Left(InvalidLength)
    else Right("Password is correct by length")
  }

  private def containsUpperCase(password: String): Either[Throwable, String] = {
        if(!password.exists(_.isUpper)) Left(MissingUppercase)
        else Right("Password is correct by upper case")
          }

  private def containsLowerCase(password: String): Either[Throwable, String] = {
    if(!password.exists(_.isLower)) Left(MissingLowercase)
    else Right("Password is correct by lower case")
      }

  private def containsNumber(password: String): Either[Throwable, String] = {
    if(!password.exists(_.isDigit)) Left(MissingNumber)
      else Right("Password is correct by containing numbers")
  }

  object InvalidLength    extends Throwable("Password must contain at least 5 characters.")
  object MissingUppercase extends Throwable("Password must contain uppercase letter.")
  object MissingLowercase extends Throwable("Password must contain lowercase letter.")
  object MissingNumber    extends Throwable("Password must contain number.")

}
