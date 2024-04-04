import os
import dotenv
from langchain.memory import ChatMessageHistory
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from flask import Flask , request , jsonify
from text_formatter import remove_special_characters

app = Flask(__name__)

dotenv.load_dotenv()

def chatWithChain(message, chat_message_history):
    chat_message_history.add_user_message(message)
    response = chain.invoke({"messages": chat_message_history.messages})
    chat_message_history.add_ai_message(response)
    return response.content

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

chat_message_history = ChatMessageHistory()
chain = prompt | chat


@app.route('/',methods=['GET'])
def hello_world():
    return 'hello'


@app.route('/chat-bot',methods=['POST'])
def ask_chat_bot():
    data = request.get_json()
    global chat_message_history  # access the global variable
    try:
        response = chatWithChain(data['text'], chat_message_history=chat_message_history)
        cleaned_response = remove_special_characters(response)
        return jsonify({'response': cleaned_response}), 200
    except KeyError:
        return jsonify({'error': 'Invalid request data: "text" field not found'}), 400
    except Exception as e:
        print(f"An error occurred: {str(e)}")
        # Reset the chat_message_history object
        chat_message_history = ChatMessageHistory()
        return jsonify({'error': 'An error occurred'}), 500


@app.route('/ask-ai', methods=['POST'])
def ask_ai():
    data = request.get_json()
    try:
        model = ChatGoogleGenerativeAI(model='gemini-pro',google_api_key=os.getenv('GOOGLE_KEY'),convert_system_message_to_human=True)
        response = model.invoke(data['text'])
        cleaned_response = remove_special_characters(response.content)
        return cleaned_response
    except Exception as e:
        print(f"An error occurred: {str(e)}")
        return jsonify({'error': 'An error occurred'}), 500
