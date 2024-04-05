import os
import dotenv
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from connect import get_message_history
dotenv.load_dotenv()


def chat_with_chain(message, message_history):
    response = chain.invoke({"messages": message_history.messages})
    return response.content


def load_message_history(username, message_history, message):
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

