-- V10__Align_with_hevy_spec.sql
-- Add missing columns to users
ALTER TABLE users ADD COLUMN IF NOT EXISTS username VARCHAR(50) UNIQUE;
ALTER TABLE users ADD COLUMN IF NOT EXISTS password_hash TEXT;
ALTER TABLE users ADD COLUMN IF NOT EXISTS profile_picture TEXT;
ALTER TABLE users ADD COLUMN IF NOT EXISTS bio TEXT;

-- Update exercises table
ALTER TABLE exercises ADD COLUMN IF NOT EXISTS category VARCHAR(50);
ALTER TABLE exercises ADD COLUMN IF NOT EXISTS equipment VARCHAR(100);
ALTER TABLE exercises ADD COLUMN IF NOT EXISTS instructions TEXT;
ALTER TABLE exercises ADD COLUMN IF NOT EXISTS is_custom BOOLEAN DEFAULT FALSE;
ALTER TABLE exercises ADD COLUMN IF NOT EXISTS created_by_user_id UUID;

-- Create routines table
CREATE TABLE IF NOT EXISTS routines (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create routine_exercises table
CREATE TABLE IF NOT EXISTS routine_exercises (
    id UUID PRIMARY KEY,
    routine_id UUID NOT NULL,
    exercise_id UUID NOT NULL,
    order_index INTEGER,
    target_sets INTEGER,
    target_reps INTEGER,
    rest_seconds INTEGER,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (routine_id) REFERENCES routines(id),
    FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);

-- Update workouts table
ALTER TABLE workouts RENAME COLUMN start_time TO started_at;
ALTER TABLE workouts RENAME COLUMN end_time TO finished_at;
ALTER TABLE workouts ADD COLUMN IF NOT EXISTS total_volume NUMERIC;
ALTER TABLE workouts ADD COLUMN IF NOT EXISTS routine_id UUID;

-- Rename exercise_sets to sets (if needed, but the spec says CREATE TABLE sets)
-- Existing table is exercise_sets. I'll create a view or rename it.
-- Let's rename exercise_sets to sets to follow the spec more closely.
ALTER TABLE exercise_sets RENAME TO sets;
ALTER TABLE sets ADD COLUMN IF NOT EXISTS duration INTEGER;
ALTER TABLE sets ADD COLUMN IF NOT EXISTS set_type VARCHAR(20);

-- Ensure sets columns match spec names
-- Current: workout_exercise_id (OK), set_number (OK), weight (OK), reps (OK), rpe (OK), completed (OK)
-- Spec: id, workout_exercise_id, set_number, weight, reps, duration, rpe, set_type, completed
