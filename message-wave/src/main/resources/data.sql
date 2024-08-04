INSERT INTO Message (id, content, recipient_phone_number, sending_status)
VALUES
    ('1bb9bdc6-e10c-4936-8fcb-d53ef0e9f2e3', 'Hello, how are you?', '+905555555001', 'PENDING'),
    ('d0057a9a-ce5e-4b86-bdf0-35d74397c60e', 'How is your day going?', '+905555555002', 'PENDING'),
    ('cd381e59-790f-4862-9aa5-02cabaeca331', 'Do you have dinner plans?', '+905555555003', 'PENDING'),
    ON CONFLICT (id) DO NOTHING;