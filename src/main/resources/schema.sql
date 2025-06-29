
-- Tutorials Table

CREATE TABLE IF NOT EXISTS tutorial (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "title" TEXT NOT NULL UNIQUE,
    "description" TEXT,
    "published" BOOLEAN NOT NULL DEFAULT FALSE
);