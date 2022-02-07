const symbols = "(){}[]+-=*/!:|";
export type Symbol = "p_open"|"p_close"|"b_open"|"b_close"|"s_open"|"s_close"|"plus"|"minus"|"mult"|"div"|"not"|"colon"|"or"
const symbolTypes: Symbol[] = [
    "p_open", "p_close", "b_open", "b_close", "s_open", "s_close", "plus", "minus", "mult", "div", "not", "colon", "or"
]
export const declare = [
    "func", "var"
];

export const end = [
    "ret", "break", "continue"
]

export const access = [
    "export", "import"
]
export const logic = [
    "if", "else", "for", "while"
]
export const types = [
    "i32", "i64", "f32", "f64"
]
export type Token = {
    type: "name"|"number"|"declare"|"newline"|"type"|"logic"|"access"|"end"|Symbol;
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
                type: symbolTypes[symbols.indexOf(val)]
            };
        } else if (val == ";") {
            token = {
                type: "newline"
            };
        } else if (declare.indexOf(val) != -1) {
            token = {
                type: "declare",
                value: val
            };
        } else if (types.indexOf(val) != -1) {
            token = {
                type: "type",
                value: val
            };
        } else if (logic.indexOf(val) != -1) {
            token = {
                type: "logic",
                value: val
            };
        } else if (access.indexOf(val) != -1) {
            token = {
                type: "access",
                value: val
            };
        } else if (end.indexOf(val) != -1) {
            token = {
                type: "end",
                value: val
            };
        } else {
            token = {
                type: "name",
                value: val
            }
        }
        tokens.push(token);
    }
    return tokens;
}