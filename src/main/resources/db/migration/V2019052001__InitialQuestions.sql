INSERT INTO Question
(id, created_at, updated_at, status, declaration_id, billType_id, name, sequence, datatype)
VALUES
(1, now(), now(), 'active', 1, 1, 'Fecha', 10, 'date'),
(2, now(), now(), 'active', 1, 1, 'RUC Proveedor', 20,'string'),
(3, now(), now(), 'active', 1, 1, 'Nombre', 30, 'string'),
(4, now(), now(), 'active', 1, 1, 'Factura', 40, 'string'),
(5, now(), now(), 'active', 1, 1, 'Subtotal', 50, 'double'),
(6, now(), now(), 'active', 1, 1, 'IVA', 60, 'double'),
(7, now(), now(), 'active', 1, 1, 'Total', 70, 'double'),
(8, now(), now(), 'active', 1, 1, 'Concepto', 80, 'string'),--(BIEN / SERVICIO)
(9, now(), now(), 'active', 1, 1, 'Descripción', 90, 'string'),--(COMPRA DE MERCADERIA / PAGO DE INSUMOS / COMPRA DE SUMINISTROS DE OFICINA)
(10, now(), now(), 'active', 1, 2, 'Fecha', 100, 'date'),
(11, now(), now(), 'active', 1, 2, 'RUC Proveedor', 110, 'string'),
(12, now(), now(), 'active', 1, 2, 'Nombre', 120, 'string'),
(13, now(), now(), 'active', 1, 2, 'Factura', 130, 'string'),
(14, now(), now(), 'active', 1, 2, 'Subtotal', 140, 'double'),
(15, now(), now(), 'active', 1, 2, 'IVA', 150, 'double'),
(16, now(), now(), 'active', 1, 2, 'Total', 160, 'double'),
(17, now(), now(), 'active', 1, 2, 'Concepto', 170, 'string'),--(BIEN / SERVICIO)
(18, now(), now(), 'active', 1, 2, 'Descripción', 180, 'string'),--(COMPRA DE MERCADERIA / PAGO DE INSUMOS / COMPRA DE SUMINISTROS DE OFICINA)
(19, now(), now(), 'active', 1, 2, '% RETENCION IVA', 190, 'double'),--(30% / 10% / 100%)
(20, now(), now(), 'active', 1, 2, 'VALOR RETENCION IVA', 200, 'double'),
(21, now(), now(), 'active', 1, 2, '%RETENCION FUENTE', 210, 'double'),--(1% / 2% / 8% / 10%)
(22, now(), now(), 'active', 1, 2, 'VALOR RETENCION FUENTE', 220, 'double');
