const symbols = "(){}[]+-=*/!:|";
const letters = /[a-z]/i;
const numbers = /[1-9]/;
export function parseString(input: string): string[] {
    const output: string[] = [];
    let str = "";
    for (let i = 0; i < input.length; i++) {
        const char = input[i];
        if (symbols.indexOf(char) != -1) {
            if (str.length > 0)
                output.push(str);
            output.push(char);
            str = "";
        }
        if (char == " ") {
            if (str.length > 0)
                output.push(str);
            str = "";
        }
        if (letters.test(char)) {
            if (str.length > 0 && numbers.test(str[0]))
                throw new InvalidNameError(i, input);
            str += char;
        }
        if (numbers.test(char)) {
            str += char;
            if (input[i+1] == ".") {
                i++;
                str += ".";
            }
        }
    }
    return output;
}

class InvalidNameError extends Error {
    constructor(idx: number, source: string) {
        super("Invalid name at index: " + idx + "\n..."+source.substring(idx-10,idx+10)+"...");
    }
}