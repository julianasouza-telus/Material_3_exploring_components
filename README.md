# Material 3 Design — Mentoria Jetpack Compose

> Repositório oficial da mentoria **Material 3 na Prática com Jetpack Compose**.  
> Contém um catálogo completo de componentes, um app de referência finalizado e um módulo em branco para desenvolvimento em aula.

---

## Objetivo da Mentoria

Esta mentoria tem como objetivo capacitar desenvolvedores Android a entender, aplicar e dominar os componentes do **Material Design 3** utilizando **Jetpack Compose**, através de uma abordagem prática e orientada a construção de interfaces reais.

Ao longo de **10 aulas**, os participantes irão explorar os principais componentes do Material 3, entender quando utilizá-los, como configurá-los corretamente e como adaptá-los para diferentes cenários de produto, seguindo boas práticas de design e desenvolvimento.

Cada encontro abordará um grupo de componentes, combinando:

- explicação conceitual
- demonstração prática no `showcase-app`
- implementação guiada no `task-app`
- tempo dedicado para experimentação no repositório

Ao final da mentoria, os participantes terão:

- compreendido a estrutura e filosofia do **Material Design 3**
- utilizado os principais **componentes de UI do Compose**
- construído interfaces consistentes, acessíveis e escaláveis
- desenvolvido autonomia para customizar e adaptar componentes em projetos reais
- adquirido confiança para aplicar **boas práticas de UI e UX no desenvolvimento Android**

---
## Como fazer um Fork e rodar o projeto

### 1. Faça o fork no GitHub

Acesse o repositório no GitHub e clique em **Fork** (canto superior direito).  
Isso cria uma cópia do repositório na sua conta pessoal.

### 2. Clone o seu fork

```bash
git clone https://github.com/SEU_USUARIO/Material3Design.git
cd Material3Design
```

### 3. Abra no Android Studio

- Abra o **Android Studio** (versão recomendada: Hedgehog ou superior)
- Selecione **File → Open** e navegue até a pasta clonada
- Aguarde o Gradle sincronizar automaticamente

### 4. Selecione o módulo para rodar

No menu **Run/Debug Configurations** (canto superior direito), escolha o módulo desejado:

| Módulo | Quando usar |
|---|---|
| `showcase-app` | Para explorar os componentes M3 |
| `final-app` | Para ver o app TaskFlow finalizado |
| `task-app` | Para desenvolver durante as aulas |

### 5. Execute no emulador ou dispositivo

Clique em **Run** (▶) ou use `Shift + F10`.

> **Dica:** Para ver os previews sem rodar o app, abra qualquer arquivo `.kt` do `showcase-app` e use a aba **Split** no Android Studio para ver o painel de preview ao lado do código.

---

## Estrutura do Repositório

O projeto é dividido em **4 módulos**, cada um com uma responsabilidade clara:

```
Material3Design/
├── core:ui          → Tema compartilhado entre todos os módulos
├── showcase-app     → Catálogo completo de componentes M3 com previews
├── final-app        → App TaskFlow completo (referência da mentoria)
└── task-app         → Módulo em branco para desenvolvimento em aula
```

---

## Módulos

### `core:ui` — Fundação do Tema

Biblioteca Android compartilhada que centraliza todos os tokens de design do projeto. Todos os outros módulos dependem deste.

| Arquivo | Conteúdo |
|---|---|
| `Color.kt` | Paleta de cores base e cores do TaskFlow (`TaskFlowPrimary`, `TaskFlowSecondary`, etc.) |
| `Theme.kt` | `Material3DesignTheme` — wrapper de tema com suporte a dark mode e dynamic color |
| `Type.kt` | Escala tipográfica customizada seguindo o type scale do M3 |
| `Dimens.kt` | Tokens de espaçamento, elevação, tamanhos de componentes e raios de borda |

**Por que usar `core:ui`?**  
Centralizar o tema evita duplicação e garante consistência visual entre `showcase-app`, `final-app` e `task-app` sem reescrever nada.

---

### `showcase-app` — Catálogo de Componentes

Aplicativo de referência com uma tela dedicada para cada componente do Material 3. Cada tela contém variações do componente, estados (enabled, disabled, error) e previews em **light e dark mode**.

#### Actions
| Arquivo | Componentes demonstrados |
|---|---|
| `actions/CommonButtons.kt` | `Button` (Filled), `OutlinedButton`, `TextButton`, `ElevatedButton`, `FilledTonalButton` — com estados enabled/disabled |
| `actions/FAB.kt` | `FloatingActionButton`, `SmallFloatingActionButton`, `LargeFloatingActionButton`, `ExtendedFloatingActionButton` |
| `actions/SegmentedButtons.kt` | `SingleChoiceSegmentedButtonRow`, `MultiChoiceSegmentedButtonRow` |

#### Communication
| Arquivo | Componentes demonstrados |
|---|---|
| `communication/Badges.kt` | `Badge`, `BadgedBox` — com e sem contagem, sobre ícones e botões |
| `communication/ProgressIndicators.kt` | `CircularProgressIndicator` (determinado e indeterminado), `LinearProgressIndicator` |
| `communication/Snackbars.kt` | `Snackbar` com action, dismiss action e duração controlada via `SnackbarHostState` |

#### Containment
| Arquivo | Componentes demonstrados |
|---|---|
| `containment/Cards.kt` | `Card`, `ElevatedCard`, `OutlinedCard`, `FilledCard` — com e sem click |
| `containment/Dialogs.kt` | `AlertDialog`, `Dialog` customizado, dialog de loading |
| `containment/List.kt` | `ListItem` — one-line, two-line, three-line, com leading/trailing content, selectable, com dividers |
| `containment/BottomSheets.kt` | `ModalBottomSheet`, `BottomSheetScaffold` com peek height |
| `containment/SideSheets.kt` | Side sheet customizado com `ModalDrawerSheet` |

#### Data Input
| Arquivo | Componentes demonstrados |
|---|---|
| `dataInput/TextFields.kt` | `TextField` e `OutlinedTextField` — label, placeholder, supporting text, error state, counter, leading/trailing icons |
| `dataInput/Checkboxes.kt` | `Checkbox` — standalone, com label, indeterminate, em lista |
| `dataInput/RadioButtons.kt` | `RadioButton` em grupo com estado controlado |
| `dataInput/Switch.kt` | `Switch` — básico, com thumb icon, em contexto de settings, disabled |
| `dataInput/Sliders.kt` | `Slider` e `RangeSlider` — contínuo e com steps |
| `dataInput/Dropdown.kt` | `ExposedDropdownMenuBox` (filled e outlined), `DropdownMenu` contextual |
| `dataInput/SearchBar.kt` | `SearchBar` e `DockedSearchBar` com sugestões |

#### Feedback
| Arquivo | Componentes demonstrados |
|---|---|
| `feedback/Tooltip.kt` | `PlainTooltip`, `RichTooltip` — em `IconButton` e `Button`, com estado persistente |

#### Layout
| Arquivo | Componentes demonstrados |
|---|---|
| `layout/ScaffoldScreen.kt` | `Scaffold` completo — `TopAppBar`, `NavigationBar`, `FAB`, `SnackbarHost` |
| `layout/SurfaceScreen.kt` | `Surface` — elevações, shapes, cores do color scheme, clickable |
| `layout/DividerScreen.kt` | `HorizontalDivider`, `VerticalDivider` — com espessura, indent e em listas |
| `layout/LazyLayouts.kt` | `LazyColumn`, `LazyColumn` com `stickyHeader`, `LazyRow` com chips, `LazyRow` com cards |

#### Navigation
| Arquivo | Componentes demonstrados |
|---|---|
| `navigation/TopAppBars.kt` | `TopAppBar`, `CenterAlignedTopAppBar`, `MediumTopAppBar`, `LargeTopAppBar` + `enterAlwaysScrollBehavior` |
| `navigation/NavigationBar.kt` | `NavigationBar` + `NavigationBarItem` com badges |
| `navigation/NavigationRail.kt` | `NavigationRail` + `NavigationRailItem` |
| `navigation/NavigationDrawer.kt` | `ModalNavigationDrawer`, `PermanentNavigationDrawer` |
| `navigation/Tabs.kt` | `PrimaryTabRow`, `SecondaryTabRow`, tabs com e sem ícone |

#### Selection
| Arquivo | Componentes demonstrados |
|---|---|
| `selection/Chips.kt` | `AssistChip`, `FilterChip`, `InputChip`, `SuggestionChip` — com ícones e estados |
| `selection/DatePickers.kt` | `DatePicker`, `DatePickerDialog`, `DateRangePicker` |
| `selection/TimePickers.kt` | `TimePicker`, `TimeInput`, dialog customizado |

---

### `final-app` — TaskFlow (Referência)

App completo que serve como **objetivo final da mentoria**. Demonstra como compor múltiplos componentes M3 em um fluxo real, com estado em memória, animações nativas e boas práticas de arquitetura.

**Funcionalidades:**
- Lista de tarefas com filtros (All / Important / Done)
- Aba de Favoritos com empty state animado
- FAB que expande/colapsa com o scroll (`ExtendedFloatingActionButton`)
- Dialog para adicionar tarefa com `OutlinedTextField` e `Switch`
- `ModalBottomSheet` para filtros com `RadioButton`
- Dialog de confirmação de exclusão
- Snackbar customizado (verde para criação, primário para exclusão)
- `SwipeToDismiss` para deletar arrastando o card

**Animações nativas utilizadas:**
| Animação | API Compose/M3 |
|---|---|
| Transição entre tabs | `AnimatedContent` com slide horizontal |
| Card cresce ao receber chips | `animateContentSize()` |
| Transição entre filtros | `AnimatedContent` com fade + scale |
| Cards entram/saem da lista | `Modifier.animateItem()` |
| Cor do título ao completar | `animateColorAsState` |
| Alpha da descrição ao completar | `animateFloatAsState` |
| Cor da estrela de favorito | `animateColorAsState` |
| Elevação do card ao pressionar | `ElevatedCard(onClick)` nativo M3 |
| FAB expande/colapsa | `ExtendedFloatingActionButton(expanded)` nativo M3 |

**Estrutura de arquivos:**
```
finalapp/
├── data/
│   └── Task.kt                  → Data class + sample tasks
├── TaskFlowApp.kt               → Root composable com estado e Scaffold
└── ui/
    ├── TaskListScreen.kt        → Tab 0: FilterChips + LazyColumn
    ├── FavoritesScreen.kt       → Tab 1: tarefas importantes + empty state
    ├── TaskCard.kt              → Card reutilizável com animações
    ├── AddTaskDialog.kt         → AlertDialog com TextField e Switch
    ├── FilterBottomSheet.kt     → ModalBottomSheet com RadioButtons
    └── DeleteConfirmDialog.kt   → AlertDialog de confirmação
```

---

### `task-app` — Módulo de Aula

Módulo **intencionalmente em branco**. É aqui que o app TaskFlow será construído do zero durante as aulas, seguindo o `final-app` como referência.

O `core:ui` já está configurado como dependência — o tema, as cores e os tokens de dimensão estão disponíveis desde o início.

---

## Conteúdo da Mentoria — 10 Aulas

### Aula 1 — Fundamentos do Material 3
Introdução ao Material Design 3, configuração de tema no Compose (`Color.kt`, `Type.kt`, `Theme.kt`) e uso de tokens de dimensão com `Dimens.kt`. Entendendo a estrutura base do `core:ui` e por que centralizar o tema.

**Showcase:** `core:ui/theme/` — Color, Theme, Type, Dimens

---

### Aula 2 — Actions & Buttons
Exploração dos principais botões (`FilledButton`, `OutlinedButton`, `TextButton`, `ElevatedButton`, `FilledTonalButton`) e hierarquia de ações. FABs e seus tamanhos (`SmallFAB`, `FAB`, `LargeFAB`, `ExtendedFAB`). Definição de estados enabled/disabled.

**Showcase:** `actions/CommonButtons.kt`, `actions/FAB.kt`, `actions/SegmentedButtons.kt`

---

### Aula 3 — Inputs & Formulários
Uso de `TextField` e `OutlinedTextField` com label, placeholder, supporting text, error state e contador de caracteres. `Checkbox`, `Switch` e `RadioButton` com gerenciamento de estado e boas práticas de UX para formulários.

**Showcase:** `dataInput/TextFields.kt`, `dataInput/Checkboxes.kt`, `dataInput/Switch.kt`, `dataInput/RadioButtons.kt`

---

### Aula 4 — Feedback & Status
Componentes de feedback como `Snackbar` (com action e dismiss), `CircularProgressIndicator`, `LinearProgressIndicator` e `Badge` / `BadgedBox`. Estados de carregamento, erro e sucesso.

**Showcase:** `communication/Snackbars.kt`, `communication/ProgressIndicators.kt`, `communication/Badges.kt`

---

### Aula 5 — Surfaces & Cards
Uso de `Card`, `ElevatedCard`, `OutlinedCard` e `FilledCard` para organização de conteúdo. `Surface` com elevação, shape e cor. Hierarquia visual e composição de layouts com contenção.

**Showcase:** `containment/Cards.kt`, `layout/SurfaceScreen.kt`

---

### Aula 6 — Navegação
Estrutura de navegação com `TopAppBar` (Small, Center, Medium, Large) e scroll behaviors. `NavigationBar`, `NavigationRail`, `NavigationDrawer` e `Tabs`. Quando usar cada padrão de navegação.

**Showcase:** `navigation/TopAppBars.kt`, `navigation/NavigationBar.kt`, `navigation/NavigationRail.kt`, `navigation/NavigationDrawer.kt`, `navigation/Tabs.kt`

---

### Aula 7 — Listas & Layouts
Construção de listas performáticas com `LazyColumn` e `LazyRow`. Cabeçalhos fixos com `stickyHeader`. `ListItem` com variações de linha. `Scaffold` como estrutura base do app. `HorizontalDivider` e `VerticalDivider`.

**Showcase:** `containment/List.kt`, `layout/LazyLayouts.kt`, `layout/ScaffoldScreen.kt`, `layout/DividerScreen.kt`

---

### Aula 8 — Dialogs & Overlays
Uso de `AlertDialog` e `Dialog` customizado. `ModalBottomSheet` e `BottomSheetScaffold`. Tooltip (`PlainTooltip`, `RichTooltip`). Entendendo quando usar cada abordagem de overlay e como lidar com estado de abertura/fechamento.

**Showcase:** `containment/Dialogs.kt`, `containment/BottomSheets.kt`, `feedback/Tooltip.kt`

---

### Aula 9 — Interações & Animações
Animações nativas do Compose: `animateColorAsState`, `animateFloatAsState`, `animateContentSize`, `AnimatedContent`, `AnimatedVisibility`, `Modifier.animateItem()`. Estados visuais com `ElevatedCard` e `ExtendedFloatingActionButton`. Como M3 já entrega animações prontas nos próprios componentes.

**Referência:** `final-app/` — todas as animações implementadas no TaskFlow

---

### Aula 10 — Bônus & Fechamento
Aula reservada para revisar conteúdo pendente, explorar componentes extras do showcase (`Chips`, `DatePickers`, `TimePickers`, `SearchBar`, `Dropdown`, `Sliders`, `SideSheets`) ou sessão aberta de dúvidas e refinamento do `task-app` construído em aula.

**Showcase:** `selection/Chips.kt`, `selection/DatePickers.kt`, `selection/TimePickers.kt`, `dataInput/SearchBar.kt`, `dataInput/Dropdown.kt`, `dataInput/Sliders.kt`, `containment/SideSheets.kt`

---

## Licença

Este repositório é de uso exclusivo para fins educacionais da mentoria.
