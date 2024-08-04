INSERT INTO Message (id, content, recipient_phone_number, sending_status)
VALUES
    ('1bb9bdc6-e10c-4936-8fcb-d53ef0e9f2e3', 'Hello, how are you?', '+905555555001', 'PENDING'),
    ('d0057a9a-ce5e-4b86-bdf0-35d74397c60e', 'How is your day going?', '+905555555002', 'PENDING'),
    ('cd381e59-790f-4862-9aa5-02cabaeca331', 'Do you have dinner plans?', '+905555555003', 'PENDING'),
    ('c55bea60-dd80-4b21-9e50-d847b46aa7d3', 'Shall we meet tomorrow?', '+905555555004', 'PENDING'),
    ('cf345162-ba8c-4584-bd26-11a880922872', 'What are you doing this weekend?', '+905555555005', 'PENDING'),
    ('c7011a89-922a-4b5d-9058-301b2bcf93a7', 'Did you finish the new project?', '+905555555006', 'PENDING'),
    ('188ccddf-11cd-46c7-8594-eb0f59e3132d', 'Are you going to the gym?', '+905555555007', 'PENDING'),
    ('5e79b2d0-93a0-4cb6-a4b8-5c7492c6601d', 'Did you get the movie tickets?', '+905555555008', 'PENDING'),
    ('f864547a-bd22-4ab3-a12a-4175e41e3834', 'What are your holiday plans?', '+905555555009', 'PENDING'),
    ('19574dd5-5f68-4381-8df8-c7c40eca22e0', 'See you soon!', '+905555555010', 'PENDING')
    ON CONFLICT (id) DO NOTHING;