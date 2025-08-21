INSERT INTO public.terms_and_conditions(
    effective_date, is_essential, created_at, updated_at, version, provider_type, link, type)
VALUES (NOW(), TRUE, NOW(), NOW(), '1.0.0', 'SOCIAL', 'https://splendid-fiction-653.notion.site/255b86814a11803e9a21e8c59ae5c2e1?source=copy_link', 'SERVICE_TERMS'),
       (NOW(), TRUE, NOW(), NOW(), '1.0.0', 'SOCIAL', 'https://splendid-fiction-653.notion.site/255b86814a118026b27bdade8e9bb589?source=copy_link', 'PRIVATE_POLICY'),

       (NOW(), TRUE, NOW(), NOW(), '1.0.0', 'GUEST', 'https://splendid-fiction-653.notion.site/242b86814a1180eeb5d0ee670aa26aa7?source=copy_link', 'SERVICE_TERMS'),
       (NOW(), TRUE, NOW(), NOW(), '1.0.0', 'GUEST', 'https://splendid-fiction-653.notion.site/244b86814a118059abf1c4995f3cd78a?source=copy_link', 'PRIVATE_POLICY')
;
