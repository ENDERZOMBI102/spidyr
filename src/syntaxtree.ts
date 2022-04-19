import { Token } from "./tokenizer.ts";

export type Root = {
    namespace: string
    imports?: string[]
    class?: Class
}

export type Class = {
    name: string
    public: boolean
    properties: (Variable|Function)[]|null
    super: string|null
    interfaces: string[]|null
}

export type Variable = {
    name: string
    public?: boolean
    type: string
}

export type Function = {
    name: string
    public: boolean
    args?: Variable[]
    type?: string
}

let i: number;

export function toSyntaxTree(tokens: Token[]): Root {
    i = 0;
    return parseRoot(tokens)
}

function parseRoot(tokens: Token[]): Root {
    var namespace = ""
    if (tokens[i++].value == "namespace") namespace = parsePath(tokens);
    if (tokens[i].type == "newline") i++;
    var imports: string[] = []
    for (; i < tokens.length; i++) {
        if (!(tokens[i].type == "access" && tokens[i].value == "import")) break;
        i++;
        imports.push(parsePath(tokens));
    }
    if (tokens[i].type == "newline") i++;

    return {
        namespace,
        imports,
        class: parseClass(tokens)
    }
}
function parsePath(tokens: Token[]): string {
    let out = "";
    for (; i < tokens.length; i++) {
        if (tokens[i].type == "name") out += tokens[i].value;
        else if (tokens[i].type == "dot") out += "/";
        else return out;
    }
    return out;
}
function parseClass(tokens: Token[]): Class|undefined {
    var isPublic = false;
    if (tokens[i].type == "access" && tokens[i].value == "export") {
        isPublic = true;
        i++
    }
    var name = parsePath(tokens);
    
    var extend = null;
    if (tokens[i].value == "ext") {
        i++;
        extend = parsePath(tokens);
    }
    var implement = [];
    if (tokens[i].value == "imp") {
        i++;
        for (; i < tokens.length; i++) {
            implement.push(parsePath(tokens))
            if (tokens[i].type == "b_open") break;
        }
    }

    i++;
    var properties = parseProperties(tokens)
    return {
        name,
        public: isPublic,
        properties: properties.length? properties: null,
        super: extend,
        interfaces: implement.length? implement: null
    }
}
function parseProperties(tokens: Token[]): (Variable|Function)[] {
    var out: (Variable|Function)[] = [];
    for (; i < tokens.length; i++) {

    }

    return out;
}
