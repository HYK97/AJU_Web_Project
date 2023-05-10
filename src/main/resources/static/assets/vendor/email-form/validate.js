(function () {
    "use strict";

    let forms = document.querySelectorAll('.email-form');

    forms.forEach(function (e) {
        e.addEventListener('submit', function (event) {
            event.preventDefault();

            let thisForm = this;

            let action = thisForm.getAttribute('action');


            if (!action) {
                displayError(thisForm, '양식 작업 속성이 설정되지 않았습니다.!');
                return;
            }
            thisForm.querySelector('.loading').classList.add('d-block');
            thisForm.querySelector('.error-message').classList.remove('d-block');
            thisForm.querySelector('.sent-message').classList.remove('d-block');

            let formData = new FormData(thisForm);

            const form = document.querySelector('.email-form');
            const nameInput = form.querySelector('input[name="name"]');
            const emailInput = form.querySelector('input[name="email"]');
            const phoneInput = form.querySelector('input[name="phone"]');
            const messageInput = form.querySelector('textarea[name="message"]');

            const nameValue = nameInput.value.trim();
            const emailValue = emailInput.value.trim();
            const phoneValue = phoneInput.value.trim();
            const messageValue = messageInput.value.trim();

            const errors = [];

            if (nameValue.length < 1 || nameValue.length > 10) {
                errors.push('성함은 1글자 이상, 10글자 이하이어야 합니다.');
                nameInput.classList.add('is-invalid');
            } else {
                nameInput.classList.remove('is-invalid');
            }

            if (emailValue.length < 4 || emailValue.length > 100) {
                errors.push('이메일은 4글자 이상, 100글자 이하이어야 합니다.');
                emailInput.classList.add('is-invalid');
            } else {
                emailInput.classList.remove('is-invalid');
            }

            if (phoneValue.length < 5 || phoneValue.length > 16) {
                errors.push('연락받을 번호는 5글자 이상, 16글자 이하이어야 합니다.');
                phoneInput.classList.add('is-invalid');
            } else {
                phoneInput.classList.remove('is-invalid');
            }

            if (messageValue.length < 1) {
                errors.push('내용을 입력해주세요.');
                messageInput.classList.add('is-invalid');
            } else {
                messageInput.classList.remove('is-invalid');
            }

            if (errors.length > 0) {
                const errorText = errors.join('<br>');
                form.querySelector('.loading').classList.remove('d-block');
                form.querySelector('.error-message').innerHTML = errorText;
                form.querySelector('.error-message').classList.add('d-block');
                return;
            }

            php_email_form_submit(thisForm, action, formData);

        });
    });

    function php_email_form_submit(thisForm, action, formData) {
        fetch(action, {
            method: 'POST',
            body: formData,
            headers: {'X-Requested-With': 'XMLHttpRequest'}
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error(`${response.status} : ${response.statusText} ${response.url}`);
                }
            })
            .then(data => {
                thisForm.querySelector('.loading').classList.remove('d-block');
                if (data.trim() == 'OK') {
                    thisForm.querySelector('.sent-message').classList.add('d-block');
                    thisForm.reset();
                } else {
                    throw new Error(data ? data : '양식 제출에 실패했으며 에서 반환된 오류 메시지가 없습니다.: ' + action);
                }
            })
            .catch((error) => {
                displayError(thisForm, error);
            });
    }

    function displayError(thisForm, error) {
        thisForm.querySelector('.loading').classList.remove('d-block');
        thisForm.querySelector('.error-message').innerHTML = "오류입니다. 나중에 시도해주세요";
        thisForm.querySelector('.error-message').classList.add('d-block');
    }

})();
