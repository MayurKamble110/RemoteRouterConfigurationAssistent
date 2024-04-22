import os
from langchain.memory import ChatMessageHistory
from langchain_google_genai import ChatGoogleGenerativeAI
from flask import Flask, request, jsonify
from text_formatter import remove_special_characters
from connect import save_message, get_raw_interface_logs,get_raw_router_logs
from lang import chat_with_chain, load_message_history
import lang

app = Flask(__name__)


@app.route('/', methods=['GET'])
def hello_world():
    return 'hello'


@app.route('/chat-bot', methods=['POST'])
def ask_chat_bot():
    data = request.get_json()
    text = data['text']
    username = data['username']
    device_id = data['deviceId']
    try:
        message_history = ChatMessageHistory()
        message_history = load_message_history(data['username'],
                                               device_id=device_id,
                                               message_history=message_history,
                                               message=data['text'])
        response = chat_with_chain(text, message_history=message_history)
        cleaned_response = remove_special_characters(response)
        save_message(username=username, human_message=text, ai_message=cleaned_response, device_id=device_id)
        return cleaned_response
    except KeyError:
        return "Key not found"
    except Exception as e:
        print(f"An error occurred: {str(e)}")
        # Reset the chat_message_history object
        lang.chat_message_history = ChatMessageHistory()
        return "an error occurred"


@app.route('/ask-ai', methods=['POST'])
def ask_ai():
    data = request.get_json()
    text = data['text']
    try:
        model = ChatGoogleGenerativeAI(model='gemini-pro', google_api_key=os.getenv('GOOGLE_KEY'),
                                       convert_system_message_to_human=True, temperature=0.1)
        response = model.invoke(text)
        cleaned_response = remove_special_characters(response.content)
        return cleaned_response
    except Exception as e:
        print(f"An error occurred: {str(e)}")
        return "An error occurred"


@app.route('/analyse-interface', methods=['POST'])
def analyse_interface():
    data = request.get_json()
    interface_id = data['interface_id']
    try:
        interface_logs = get_raw_interface_logs(interface_id)
        if interface_logs is None:
            return 'NO_DATA'
        elif interface_logs == 'NO_DEVICE':
            return 'NO_DEVICE'
        model = ChatGoogleGenerativeAI(model='gemini-pro', google_api_key=os.getenv('GOOGLE_KEY'),
                                       convert_system_message_to_human=True,temperature=0.1)
        prompt = ("You are a helpful network administration advisor. "
                  "I am providing you some logs of router's interface configuration. "
                  "You have to analyse them and provide a short summary about the state of the interface"
                  ", vulnerabilities the router may "
                  "be exposed to and recommendations to mitigate the risks."
                  "All these 3 fields to be in a new paragraph. "
                  "The response must under 100 words. "
                  "Here are the logs ")
        response = model.invoke(prompt + interface_logs)
        return response.content
    except Exception as e:
        print("An error occurred : ", type(e))
        return "null"


@app.route('/analyse-router', methods=['POST'])
def analyse_router():
    data = request.get_json()
    router_id = data['device_id']
    try:
        router_logs = get_raw_router_logs(router_id)
        if router_logs is None:
            return 'NO_DATA'
        elif router_logs == 'NO_DEVICE':
            return 'NO_DEVICE'
        model = ChatGoogleGenerativeAI(model='gemini-pro', google_api_key=os.getenv('GOOGLE_KEY'),
                                       convert_system_message_to_human=True,temperature=0.1)
        prompt = ("You are a helpful network administration advisor. "
                  "I am providing you some logs of router configuration. "
                  "You have to analyse them and provide a short summary about the state of the router, "
                  "vulnerabilities the router may be exposed to and recommendations to mitigate the "
                  "risks.All these 3 fields to be in a new paragraph. "
                  "The response must be under 100 words. Here are the logs ")
        response = model.invoke(prompt + router_logs)
        return response.content
    except Exception as e:
        print("an error occurred", type(e))
        return "null"

