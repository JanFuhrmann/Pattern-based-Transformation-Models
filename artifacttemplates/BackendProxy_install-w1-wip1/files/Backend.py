import pika

ip = '192.168.178.83'
requestQueueName = 'RequestQueue'
replyQueueName = 'ReplyQueue'

connection = pika.BlockingConnection(pika.ConnectionParameters(host=ip))
channel = connection.channel()

channel.queue_declare(queue=requestQueueName)
def on_request_received(ch, method, properties, body):
    print(f"Received message: {body} with correlation id {properties.correlation_id}")
    replyMessage = f'Reply to {properties.correlation_id}'
    ch.basic_publish('', routing_key=properties.reply_to, body = replyMessage)
channel.basic_consume(queue=requestQueueName, on_message_callback=on_request_received, auto_ack=True)

print(f"Starting Server")
channel.start_consuming()