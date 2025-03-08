${SegmentFile}

${Segment.OnInit}
	; Borrowed the following from PAL 2.2, Remove on release of PAL 2.2
		; Work out if it's 64-bit or 32-bit
	System::Call kernel32::GetCurrentProcess()i.s
	System::Call kernel32::IsWow64Process(is,*i.r0)
	${If} $0 == 0
		StrCpy $Bits 32
		Rename "$EXEDIR\App\filezilla64\docs" "$EXEDIR\App\filezilla\docs"
		Rename "$EXEDIR\App\filezilla64\locales" "$EXEDIR\App\filezilla\locales"
		Rename "$EXEDIR\App\filezilla64\resources" "$EXEDIR\App\filezilla\resources"
	${Else}
		StrCpy $Bits 64
		Rename "$EXEDIR\App\filezilla\docs" "$EXEDIR\App\filezilla64\docs"
		Rename "$EXEDIR\App\filezilla\locales" "$EXEDIR\App\filezilla64\locales"
		Rename "$EXEDIR\App\filezilla\resources" "$EXEDIR\App\filezilla64\resources"
	${EndIf}
!macroend

${SegmentPrePrimary}
	ExpandEnvStrings $0 "%PAL:DataDir%"
	ExpandEnvStrings $1 "%PAL:AppDir%"
	ExpandEnvStrings $4 "%PAL:LastDrive%"
	ExpandEnvStrings $5 "%PAL:Drive%"
	ExpandEnvStrings $2 "%PAL:LastPortableAppsBaseDir%"
	ExpandEnvStrings $3 "%PAL:PortableAppsBaseDir%"
	ExpandEnvStrings $6 "%PAL:LastDrive%%PAL:LastPackagePartialDir%"
	ExpandEnvStrings $7 "%PAL:Drive%%PAL:PackagePartialDir%"
	
	${If} ${FileExists} "$0\settings\queue.sqlite3"
		${If} $6 != $7
			nsExec::Exec `"$1\Bin\sqlite3.exe" "$0\settings\queue.sqlite3" "UPDATE local_paths SET path = '$7' || SUBSTR(path,(LENGTH('$6')+1)) WHERE path LIKE '$6%';"`
		${EndIf}
		${If} $2 != $3
			nsExec::Exec `"$1\Bin\sqlite3.exe" "$0\settings\queue.sqlite3" "UPDATE local_paths SET path = '$3' || SUBSTR(path,(LENGTH('$2')+1)) WHERE path LIKE '$2%';"`
		${EndIf}
		${If} $4 != $5
			nsExec::Exec `"$1\Bin\sqlite3.exe" "$0\settings\queue.sqlite3" "UPDATE local_paths SET path = '$5' || SUBSTR(path,(LENGTH('$4')+1)) WHERE path LIKE '$4%';"`
		${EndIf}
	${EndIf}
!macroend
