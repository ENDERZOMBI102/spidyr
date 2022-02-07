import { Token } from "./tokenizer.ts";

export type SyntaxTree = (Token|SyntaxTree)[];

let i: number;

export function toSyntaxTree(tokens: Token[]): SyntaxTree {
    i = 0;
    return toSyntaxTreeInternal(tokens);
}

function toSyntaxTreeInternal(tokens: Token[]): SyntaxTree {
    const tree: SyntaxTree = [];
    for (; i < tokens.length; i++) {
        let token = tokens[i];
        switch (token.type) {
            case "access":
                break;
            case "declare":
                break;
            case "logic":
                break;
            case "name":
                break;
            case "newline":
                break;
            case "number":
                break;
            case "symbol":
                break;
            case "type":
                break;
        }
    }
    return tree;
}