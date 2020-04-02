Android_transport - папка проекта. Все остальные файлы, находящиеся в корне - основные файла проекта, вынесенные для удобства
	и представлены с адресами и описанием.

activity_main.xml - Android_transport\app\src\main\res\layout - xml-файлы с описанием "activities" (окна приложения)
activity_order.xml - Android_transport\app\src\main\res\layout - тоже самое, только описание окна, окрывающегося при нажатии кнопки
	"заказать машину"


colors.xml - "Android_transport\app\src\main\res\values" - xml-файл с шаблонами цветов
string.xml - "Android_transport\app\src\main\res\values" - xml-файл с строковым наполнением (считается хорошим стилем выносить 
все строковые поля в отдельный файл и ссылаться на него)

MainActivity.java - "Android_transport\app\src\main\java\com\example\android_transport" - java-файл с кодом вызова конструктора activity_main,
	а также алгоритмами работы при нажатиина кнопки и т.д..
OrderActivity.java - "Android_transport\app\src\main\java\com\example\android_transport" - java-файл -//- activity_order -//-
MainSenderClass.java - Android_transport\app\src\main\java\com\example\android_transport" - java-файл с реализацией функций отправки сообщения
JSSEProvider.java - Android_transport\app\src\main\java\com\example\android_transport" - java-файл с кодом для подключения  к серверу почты