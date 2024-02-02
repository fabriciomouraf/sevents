#!/bin/sh
# wait-for.sh - Um script para aguardar outro serviço estar disponível.

TIMEOUT=15
QUIET=0

echoerr() {
    if [ "$QUIET" -eq 0 ]; then echo "$@" 1>&2; fi
}

usage() {
    exitcode="$1"
    cat << USAGE >&2
Uso:
    $cmdname host:port [-t timeout] [-- command args]
    -q | --quiet                        Não exibir nenhuma mensagem
    -t TIMEOUT | --timeout=timeout      Tempo limite em segundos, padrão 15
    -- COMMAND ARGS                     Executar comando com seus argumentos após a verificação
USAGE
    exit "$exitcode"
}

wait_for() {
    for i in `seq $TIMEOUT` ; do
        nc -z "$HOST" "$PORT" > /dev/null 2>&1

        result=$?
        if [ $result -eq 0 ] ; then
            if [ $TIMEOUT -eq 0 ]; then
                echo "Serviço disponível"
            fi
            exit 0
        fi
        sleep 1
    done
    echo "Operação excedeu o tempo limite" >&2
    exit 1
}

while [ $# -gt 0 ]
do
    case "$1" in
        *:* )
        HOST=$(printf "%s\n" "$1"| cut -d : -f 1)
        PORT=$(printf "%s\n" "$1"| cut -d : -f 2)
        shift 1
        ;;
        -q | --quiet)
        QUIET=1
        shift 1
        ;;
        -t)
        TIMEOUT="${2:-$TIMEOUT}"
        shift 2
        ;;
        --timeout=*)
        TIMEOUT="${1#*=}"
        shift 1
        ;;
        --)
        shift
        break
        ;;
        --help)
        usage 0
        ;;
        *)
        echoerr "Erro inesperado: $1"
        usage 1
        ;;
    esac
done

if [ "$#" -eq 0 ]; then
    echoerr "Nenhum comando informado"
    usage 1
fi

wait_for

exec "$@"