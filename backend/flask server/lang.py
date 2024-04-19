import os
import dotenv
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder

import connect
from connect import get_message_history
dotenv.load_dotenv()


def chat_with_chain(message, message_history):
    response = chain.invoke({"messages": message_history.messages})
    return response.content


def load_message_history(username, device_id, message_history, message):
    print(connect.get_raw_router_logs(device_id))
    raw_router_logs = connect.get_raw_router_logs(device_id)
    if (raw_router_logs is not None) and (raw_router_logs != "NO_DEVICE"):
        print("ucl")
        message_history.add_user_message(connect.get_raw_router_logs(device_id) +
                                         "Given is the configuration logs of my router device.")
        message_history.add_ai_message("OK")
    messages = get_message_history(username=username)
    for m in messages:
        print(m[0])
        message_history.add_user_message(m[0])
        message_history.add_ai_message(m[1])
    message_history.add_user_message(message)
    return message_history


chat = ChatGoogleGenerativeAI(model='gemini-pro', convert_system_message_to_human=True,
                              temperature=0.5, google_api_key=os.getenv('GOOGLE_KEY'))

prompt = ChatPromptTemplate.from_messages(
    [
        (
            "system",
            "You are a network administrator chatbot. Answer all questions in two or three lines",
        ),
        MessagesPlaceholder(variable_name="messages"),
    ]
)

chain = prompt | chat
