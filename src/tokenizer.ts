const symbols = "(){}[]+-=*/!:|";

export type Token = {
    type: "name"|"number"|"symbol"|"newline";
    value?: string|number
};

export function tokenize(input: string[]): Token[] {
    const tokens: Token[] = [];
    for (let i = 0; i < input.length; i++) {
        const val = input[i];
        let token: Token;
        if (!isNaN(parseFloat(val))) {
            token = {
                type: "number",
                value: parseFloat(val)
            };
        } else if (symbols.indexOf(val) != -1) {
            token = {
                type: "symbol",
                value: val
            };
        } else if (val == ";") {
            token = {
                type: "newline"
            }
        } else {
            token = {
                type: "name",
                value: val
            };
        }
        tokens.push(token);
    }
    return tokens;
}