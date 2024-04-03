import re

def remove_special_characters(text) ->str:
    text = re.sub(r'\*\*', '', text)
    cleaned_text = re.sub(r'\`\`\`','',text)
    cleaned_text = cleaned_text.replace('\n','')
    return cleaned_text

# # Example usage:
# input_text = input("Enter the text: ")
# cleaned_text = remove_special_characters(input_text)
# print("Text after removing special characters:", cleaned_text)
