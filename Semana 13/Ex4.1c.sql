SELECT cliente.nome
FROM cliente
LEFT JOIN pedido
  ON cliente.id_cliente = pedido.id_cliente
WHERE pedido.id_cliente IS NULL;