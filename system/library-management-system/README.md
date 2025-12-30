# نظام إدارة المكتبة (Library Management System - LMS)

## الوصف
هذا المشروع هو تطبيق بسيط لنظام إدارة مكتبة (LMS) تم تطويره باستخدام **Java 17** و **Maven**. الهدف الأساسي من المشروع هو إظهار التطبيق العملي لمبادئ التصميم الموجهة للكائنات (OOP)، وتحديداً مبادئ **SOLID** وأنماط التصميم (Design Patterns) في بيئة برمجية حقيقية وقابلة للتشغيل.

## الميزات الرئيسية
*   إدارة الكتب (إضافة، عرض).
*   عمليات الإعارة والإرجاع.
*   البحث المرن عن الكتب باستخدام استراتيجيات مختلفة.
*   محاكاة قاعدة بيانات باستخدام نمط Singleton.

## كيفية البناء والتشغيل

### المتطلبات
*   Java Development Kit (JDK) 17 أو أحدث.
*   Apache Maven.

### 1. البناء (Compilation)
انتقل إلى المجلد الرئيسي للمشروع (`library-management-system`) وقم بتشغيل الأمر التالي:

```bash
mvn clean install
```

### 2. التشغيل من سطر الأوامر
بعد البناء الناجح، يمكنك تشغيل التطبيق باستخدام الأمر التالي:

```bash
java -cp target/library-management-system-1.0-SNAPSHOT.jar com.manus.lms.App
```

### 3. التشغيل في بيئة التطوير المتكاملة (IDE)
يمكنك استيراد المشروع بسهولة إلى أي IDE يدعم Maven (مثل IntelliJ IDEA أو Eclipse):
1.  **استيراد المشروع:** اختر "Import Project" أو "Open" وحدد ملف `pom.xml` في المجلد الرئيسي.
2.  **تشغيل:** قم بتشغيل الفئة الرئيسية `com.manus.lms.App`.

## معمارية المشروع

تم تنظيم المشروع في حزم (Packages) تعكس مسؤوليات واضحة، مما يدعم مبادئ SOLID:

| الحزمة | المسؤولية |
| :--- | :--- |
| `com.manus.lms.model` | تحتوي على فئات البيانات الأساسية (مثل `Book`، `FictionBook`). |
| `com.manus.lms.repository` | تحتوي على واجهات وتطبيقات للوصول إلى البيانات (DIP). |
| `com.manus.lms.service` | تحتوي على منطق الأعمال الرئيسي (SRP). |
| `com.manus.lms.factory` | تحتوي على المكونات المسؤولة عن إنشاء الكائنات (Factory Method). |
| `com.manus.lms.strategy` | تحتوي على واجهات وتطبيقات لخوارزميات البحث (Strategy). |
| `com.manus.lms.util` | تحتوي على فئات مساعدة، مثل مدير قاعدة البيانات (Singleton). |

## تطبيق مبادئ SOLID

تم تطبيق ثلاثة مبادئ رئيسية من SOLID لضمان تصميم مرن وقابل للصيانة:

| المبدأ | الوصف | التطبيق في الكود |
| :--- | :--- | :--- |
| **Single Responsibility Principle (SRP)** | يجب أن يكون للفئة سبب واحد فقط للتغيير (مسؤولية واحدة). | **`LibraryService`**: مسؤولة فقط عن منطق الأعمال (إضافة، إعارة، إرجاع، بحث). **`BookRepositoryImpl`**: مسؤولة فقط عن عمليات الوصول إلى البيانات. **`LibraryDBManager`**: مسؤولة فقط عن إدارة "اتصال قاعدة البيانات" (محاكاة). |
| **Open/Closed Principle (OCP)** | يجب أن تكون الكيانات البرمجية مفتوحة للتوسع ومغلقة للتعديل. | **`Book` فئة مجردة**: يمكن إضافة أنواع جديدة من الكتب (`FictionBook`، `NonFictionBook`) عن طريق التوسع دون تعديل الفئة الأساسية. **`ISearchStrategy`**: يمكن إضافة استراتيجيات بحث جديدة دون تعديل فئة `LibraryService`. |
| **Dependency Inversion Principle (DIP)** | يجب أن تعتمد الوحدات عالية المستوى على التجريدات (الواجهات) وليس على التطبيقات الملموسة. | **`LibraryService`** (وحدة عالية المستوى) تعتمد على **`IBookRepository`** و **`ISearchStrategy`** (تجريدات) بدلاً من الاعتماد المباشر على **`BookRepositoryImpl`** أو استراتيجيات البحث الملموسة. يتم حقن التبعيات في المُنشئ. |

## تطبيق أنماط التصميم (Design Patterns)

تم تطبيق ثلاثة أنماط تصميم رئيسية لتحسين هيكل الكود وقابليته لإعادة الاستخدام:

| النمط | الوصف | التطبيق في الكود |
| :--- | :--- | :--- |
| **Singleton** | يضمن وجود مثيل واحد فقط من الفئة ويوفر نقطة وصول عالمية إليه. | **`com.manus.lms.util.LibraryDBManager`**: يضمن وجود مثيل واحد فقط لمحاكاة اتصال قاعدة البيانات، مما يمنع الوصول المتعدد غير المتحكم فيه. |
| **Factory Method** | يوفر واجهة لإنشاء الكائنات في فئة أساسية، ولكنه يسمح للفئات الفرعية بتغيير نوع الكائنات التي سيتم إنشاؤها. | **`com.manus.lms.factory.IBookFactory`** و **`com.manus.lms.factory.BookFactory`**: تُستخدم لإنشاء أنواع مختلفة من الكتب (`FictionBook`، `NonFictionBook`) بناءً على البيانات المدخلة، مما يفصل منطق الإنشاء عن الكود الذي يستخدم الكائنات. |
| **Strategy** | يحدد عائلة من الخوارزميات، ويغلف كل واحدة منها، ويجعلها قابلة للتبديل. | **`com.manus.lms.strategy.ISearchStrategy`** و تطبيقاتها (`SearchByTitleStrategy`، `SearchByAuthorStrategy`): تسمح بتبديل خوارزمية البحث في `LibraryService` في وقت التشغيل دون تعديل الكود الأساسي للخدمة. |

## مثال على الإخراج (من تشغيل `App.java`)

```
--- نظام إدارة المكتبة (LMS) ---

LibraryDBManager: Database connection initialized (Singleton).
Singleton Check: dbManager1 == dbManager2 is true
DB: Added book: The Lord of the Rings
DB: Added book: Sapiens: A Brief History of Humankind
DB: Added book: The Hobbit

--- جميع الكتب في المكتبة ---
Title: The Lord of the Rings, Author: J.R.R. Tolkien, ISBN: 978-0618260292, Borrowed: false
Title: Sapiens: A Brief History of Humankind, Author: Yuval Noah Harari, ISBN: 978-0062316097, Borrowed: false
Title: The Hobbit, Author: J.R.R. Tolkien, ISBN: 978-0345339683, Borrowed: false

--- البحث عن طريق العنوان (Strategy 1) ---
Title: Sapiens: A Brief History of Humankind, Author: Yuval Noah Harari, ISBN: 978-0062316097, Borrowed: false

--- البحث عن طريق المؤلف (Strategy 2) ---
Title: The Lord of the Rings, Author: J.R.R. Tolkien, ISBN: 978-0618260292, Borrowed: false
Title: The Hobbit, Author: J.R.R. Tolkien, ISBN: 978-0345339683, Borrowed: false

--- عمليات الإعارة والإرجاع ---
Service: Book borrowed successfully: The Lord of the Rings
Service: Failed to borrow book with ISBN: 978-0618260292
Service: Book returned successfully: The Lord of the Rings
Service: Failed to return book with ISBN: 978-0618260292

--- حالة الكتب بعد العمليات ---
Title: The Lord of the Rings, Author: J.R.R. Tolkien, ISBN: 978-0618260292, Borrowed: false
Title: Sapiens: A Brief History of Humankind, Author: Yuval Noah Harari, ISBN: 978-0062316097, Borrowed: false
Title: The Hobbit, Author: J.R.R. Tolkien, ISBN: 978-0345339683, Borrowed: false
```
