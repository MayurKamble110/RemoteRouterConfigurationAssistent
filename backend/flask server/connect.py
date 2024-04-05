import psycopg2

dbname = "postgres"
user = "admin"
password = "admin"
host = "localhost"
port = "5432"

connection = psycopg2.connect(
    dbname=dbname,
    user=user,
    password=password,
    host=host,
    port=port
)

cursor = connection.cursor()


def save_message(username, human_message, ai_message):
    try:
        query = "INSERT INTO message_history (username,human_message,ai_message) VALUES (%s,%s,%s)"
        data = (username, human_message, ai_message)
        cursor.execute(query, data)
        connection.commit()
    except Exception as e:
        print(e)


def get_message_history(username):
    try:
        query = "SELECT human_message, ai_message FROM message_history WHERE username = %s"
        cursor.execute(query, (username,))
        message_history = cursor.fetchall()
        return message_history
    except Exception as e:
        print("Error loading message history:", e)
        return None


def get_raw_interface_logs(interface_id):
    try:
        query = "SELECT raw_logs FROM device_interface WHERE interface_id = %s"
        cursor.execute(query, (interface_id,))
        logs = cursor.fetchall()
        return logs[0][0]
    except Exception as e:
        print(type(e))


def get_raw_router_logs(device_id):
    try:
        query = "SELECT raw_logs FROM network_devices WHERE device_id = %s"
        cursor.execute(query, (device_id,))
        logs = cursor.fetchall()
        return logs[0][0]
    except Exception as e:
        print(type(e))