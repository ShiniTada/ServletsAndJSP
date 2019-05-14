


input.validity = {
    valid: false, // Поле валидно
    customError: false, // Установленно специальное сообщение ошибки
    valueMissing: false // Отсутствует обязательное значение
};

function CustomValidation() { }

CustomValidation.prototype = {
    // Установим пустой массив сообщений об ошибках
    invalidities: [],

    // Метод, проверяющий валидность
    checkValidity: function(input) {
        var validity = input.validity;

        if (validity.valid) {
            this.addInvalidity('Wrong login or password');
        }

        if (validity.valueMissing) {
            this.addInvalidity('Empty login or password');
        }
    },

    // Добавляем сообщение об ошибке в массив ошибок
    addInvalidity: function(message) {
        this.invalidities.push(message);
    },

    // Получаем общий текст сообщений об ошибках
    getInvalidities: function() {
        return this.invalidities.join('. \n');
    }
};

// Добавляем обработчик клика на кнопку отправки формы
submit.addEventListener('click', function(e) {
    // Пройдёмся по всем полям
    for (var i = 0; i < inputs.length; i++) {

        var input = inputs[i];

        // Проверим валидность поля, используя встроенную в JavaScript функцию checkValidity()
        if (!input.checkValidity()) {
            var inputCustomValidation = new CustomValidation(); // Создадим объект CustomValidation
            inputCustomValidation.checkValidity(input); // Выявим ошибки
            var customValidityMessage = inputCustomValidation.getInvalidities(); // Получим все сообщения об ошибках
            input.setCustomValidity(customValidityMessage); // Установим специальное сообщение об ошибк е

        } // закончился if
    } // закончился цикл
});