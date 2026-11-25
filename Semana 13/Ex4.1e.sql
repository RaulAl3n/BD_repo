SELECT cliente.nome,
       pedido.id_pedido
FROM cliente
INNER JOIN pedido
  ON cliente.id_cliente = pedido.id_cliente
  AND cliente.id_cliente = 1