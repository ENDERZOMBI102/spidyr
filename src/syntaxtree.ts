//@ts-ignore: deno lang server not working on vsc
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
        const token = tokens[i];
        switch (token.type) {
            case "access":
                break;
            case "declare":
                break;
            case "end":
                break;
            case "logic":
                break;
            case "name":
                break;
            case "newline":
                break;
            case "number":
                break;
            case "type":
                break;
            default:
                break;
        }
    }
    return tree;
}