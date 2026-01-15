# go to pgadmin http://localhost:5050
# register server

# Databases → springai_db → Query Tool

CREATE EXTENSION IF NOT EXISTS vector;

CREATE SCHEMA spring_ai;

GRANT USAGE, CREATE ON SCHEMA spring_ai TO spring_user;

ALTER ROLE spring_user SET search_path TO spring_ai, public;

CREATE TABLE spring_ai.vector_store (
    id UUID PRIMARY KEY,
    content TEXT,
    metadata JSONB,
    embedding VECTOR(768)
);

CREATE INDEX vector_store_embedding_idx
ON spring_ai.vector_store
USING ivfflat (embedding vector_cosine_ops)
WITH (lists = 100);

ANALYZE spring_ai.vector_store;