Задание 4.

*Выполняем в рамках одного проекта. Части ДЗ разделяем по экранам, создав развилку, показывающуюся при старте приложения.*


  Часть 1) Создать 4 фрагмента A->B->C->D. Каждый открывает следующий фрагмент:

 - На фрагменте А отображается кнопка перехода на фрагмент B.

 - На фрагменте B отображается кнопки перехода на фрагмент C, а также кнопка "Назад", которая возвращает нас на фрагмент А. При переходе на фрагмент C, при нажатии кнопки, мы также должны передать строку с фразой "Hello Fragment C".

 - На фрагменте C отображается текстовое поле, в котором будет отображаться строка, приходящая из фрагмента B. Также отображаются кнопки перехода на фрагмент D и на фрагмент А (при этом должна сбрасываться вся цепочка вызовов).

 - На фрагменте D отображается кнопка перехода на фрагмент B (сброс всей цепочки до фрагмента B).
   
Использовать стандартные механизмы навигации для фрагментов.


  Часть 2) Написать приложение, которое будет отображать список пользователей (4 элемента) с полями - фото/имя/фамилия/номер телефона. При нажатии на элемент списка должна открываться страница для редактирования информации о пользователе. Для реализации использовать фрагменты.

 - Создать фрагмент "Список пользователей", который будет содержать список пользователей с их данными.
   
 - Создать фрагмент "Редактирование информации о пользователе", который будет содержать всю доступную информацию о выбранном пользователе с возможностью её редактирования.
   
 - Реализовать навигацию между фрагментами при помощи стандартных механизмов навигации для фрагментов.
   
 - Обеспечить возможность редактирования информации о пользователе, её сохранения и отображения (обновленной информации) на экране "Список пользователей" с помощью FragmentResultApi.

Выполнить до 07.03.2024 23:59:00.
