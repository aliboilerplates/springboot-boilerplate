
INSERT INTO tutorial (title, description, published) VALUES
('Python Course','Beginner Python Course', false) ON CONFLICT (title) DO NOTHING;