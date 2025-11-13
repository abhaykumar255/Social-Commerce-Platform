-- Insert default roles
INSERT INTO roles (id, name, description, created_at, updated_at, version, deleted)
VALUES
    (gen_random_uuid(), 'ROLE_USER', 'Standard user role', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ROLE_ADMIN', 'Administrator role', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ROLE_MODERATOR', 'Moderator role', NOW(), NOW(), 0, false)
ON CONFLICT (name) DO NOTHING;

-- Insert default permissions
INSERT INTO permissions (id, name, description, resource, action, created_at, updated_at, version, deleted)
VALUES
    (gen_random_uuid(), 'USER_READ', 'Read user information', 'USER', 'READ', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'USER_WRITE', 'Create and update users', 'USER', 'WRITE', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'USER_DELETE', 'Delete users', 'USER', 'DELETE', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'PRODUCT_READ', 'Read product information', 'PRODUCT', 'READ', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'PRODUCT_WRITE', 'Create and update products', 'PRODUCT', 'WRITE', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ORDER_READ', 'Read order information', 'ORDER', 'READ', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ORDER_WRITE', 'Create and update orders', 'ORDER', 'WRITE', NOW(), NOW(), 0, false)
ON CONFLICT (name) DO NOTHING;
